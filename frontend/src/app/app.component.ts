import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
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



}
