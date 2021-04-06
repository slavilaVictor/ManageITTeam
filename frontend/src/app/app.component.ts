import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Member } from './model/member';
import { MemberService } from './service/member.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontITTeam';
  public members: Member[];
  public editMember: Member;
  public deleteMember: Member;
  
  constructor(private memberService: MemberService){}

  ngOnInit(): void {
    this.getMembers();
  }

  public getMembers(): void{
    this.memberService.getMembers().subscribe(
      (response: Member[])=>{
        this.members = response;
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  // addForm.value will be a JSON interpretation
  public onAddMember(addForm: NgForm): void{
    document.getElementById('add-member-form').click();
    this.memberService.addMember(addForm.value).subscribe(
      (response: Member) => {
        console.log(response);
        this.getMembers();
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }

  public onEditMember(member: Member): void{
    this.memberService.updateMember(member).subscribe(
      (response: Member) => {
        console.log(response);
        this.getMembers();
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }

  public onDeleteMember(memberId: number): void{
    this.memberService.deleteMember(memberId).subscribe(
      (response: void) => {
        console.log(response);
        this.getMembers();
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }

  // SEE BOOTSTRAP MODAL FOR MORE INFORMATION
  //member-> the member that I use
  //mode -> what I want to do with this member, in order to know which modal I open
  public onOpenModal(member: Member, mode: string): void{
    const container = document.getElementById('main-container');
    //create a button programmatically; I can do the same thing in html
    const button = document.createElement('button');
    button.type='button';
    // I will hide  my button in my UI, because I already have different buttons
    button.style.display='none';
    button.setAttribute('data-toggle', 'modal');
    // I want to see what button was pressed
    if(mode === 'add'){
      button.setAttribute('data-target', '#addMemberModal');
    }
    if(mode === 'edit'){
      this.editMember = member;
      button.setAttribute('data-target', '#updateMemberModal');
    }
    if(mode === 'delete'){
      this.deleteMember = member;
      button.setAttribute('data-target', '#deleteMemberModal');
    }
    // In this moment, this button only exists in the ts file. I have to find a way to put it into the html
    container.appendChild(button);
    button.click();
  }



}
