package eu.victor.ro.ManageITTeam.repo;

import eu.victor.ro.ManageITTeam.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//  Member - the type of the class and
// Long - its primary key
public interface MemberRepo extends JpaRepository<Member, Long> {
}
