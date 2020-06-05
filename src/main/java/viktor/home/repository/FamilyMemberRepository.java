package viktor.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viktor.home.domain.FamilyMember;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
}
