package eu.victor.ro.ManageITTeam.service;

import eu.victor.ro.ManageITTeam.model.Member;
import eu.victor.ro.ManageITTeam.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// A service hold the business logic
@Service
public class MemberService {
    // I take my repo here
    private final MemberRepo memberRepo;

    // I inject this via constructor
    // @Autowired is optional here; being injecting via constructor, I can get rid of @Autowired
    @Autowired
    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    public Member addMember(Member member){
        // I generate a member code because I do not want the user to give a code manually
        member.setMemberCode(UUID.randomUUID().toString());
        // The "save" method comes from my repo
        return memberRepo.save(member);
    }

    public List<Member> findAllMembers(){
        return memberRepo.findAll();
    }

    public Member updateMember(Member member){
        return memberRepo.save(member);
    }


    public void deleteMember(Long id){
        // my repo does not have a "delete" method, so I create my own method, named "deleteMemberById"
        memberRepo.deleteMemberById(id);
    }

    // Another example for QUERY METHODS
    public Member findMemberById(Long id){
        // If you find a member with that id, "memberRepo.findMemberById(id)" will be executed
        // However, if you do not find, you will throw an exception
        return memberRepo.findMemberById(id)
                .orElseThrow( () -> new MemberNotFoundException("Member with id " + id + " was not found"));
    }
}
