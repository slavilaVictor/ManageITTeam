package eu.victor.ro.ManageITTeam.controller;

import eu.victor.ro.ManageITTeam.model.Member;
import eu.victor.ro.ManageITTeam.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    // Bring the service, because I need to use it
    private final MemberService memberService;

    // Inject the service in the constructor
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // I want to retrieve all the members, so I create such a method. It will be a GET Request
    // My method will return an HTTPResponse. I can use the class ResponseEntity, which is a generic
    // Being a generic, in the body I have to say what I want to return. In this case, a List, which is also a generic
    // In this case, a list of objects of type Member
    // As I said in the design, the business logic is not here. All I have to do is to call the service
    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAllMembers(){
        List<Member> members = memberService.findAllMembers();
        // members will be in the body of the response. I also have to say the HTTPStatus code)
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") Long id){
        Member member = memberService.findMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // In order to add a member, I will make a POST Request
    // The parameter of the "addMember" I'm expecting to be the whole member, which will come in a JSON format(in the body of the request)
    @PostMapping("/add")
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        // "newMember" just to be different from member, which is the parameter given to this function
        Member newMember = memberService.addMember(member);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    // Update the member - it will be a PUT Request
    @PutMapping("/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member){
        // In this case, the member which I pass as a perimeter already exists in the database
        Member updateMember = memberService.updateMember(member);
        return new ResponseEntity<>(updateMember, HttpStatus.OK);
    }

    // Delete a member - it will be a DELETE Request
    @PutMapping("/delete/{id}")
    // "?" means that it returns void
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id){
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
