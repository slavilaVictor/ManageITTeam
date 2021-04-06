import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Member } from '../model/member';

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private apiServerURL = '';

  constructor(private http: HttpClient) { }

  public getMembers(): Observable<Member[]>{
    return this.http.get<Member[]>(`${this.apiServerURL}/member/all`);
  }

  public addMember(member: Member): Observable<Member>{
    return this.http.post<Member>(`${this.apiServerURL}/member/add`,member);
  }

  public updateMember(member: Member): Observable<Member>{
    return this.http.put<Member>(`${this.apiServerURL}/member/update`, member);
  }

  public deleteMember(memberId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerURL}/member/delete/${memberId}`);
  }

}
