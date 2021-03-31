package eu.victor.ro.ManageITTeam.repo;

import eu.victor.ro.ManageITTeam.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//  Member - the type of the class and
// Long - its primary key
public interface MemberRepo extends JpaRepository<Member, Long> {
    // VERY IMPORTANT: naming this method like this, Spring is able to understand this language
    // It means that is creates the query for us to delete a member knowing his id
    // This is called QUERY METHOD( because of the naming convention)
    void deleteMemberById(Long id);

    // Another QUERY METHOD
    // Because I have find, Spring will do a select, where id(ById) is equal to the is that I pass as a parameter( Long id)
    // I return an optional, because it is possible that I do not have that specific member
    Optional<Member> findMemberById(Long id);
}
