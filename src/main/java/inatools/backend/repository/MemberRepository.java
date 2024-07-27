package inatools.backend.repository;

import inatools.backend.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원 조회
    Optional<Member> findByUsername(String username);

    // 종복 회원 검사
    Boolean existsByUserId(String userId);
}
