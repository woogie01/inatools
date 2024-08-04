package inatools.backend.repository;

import inatools.backend.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원 조회
    Optional<Member> findByUserId(String userId);

    // 중복 아이디 검사
    boolean existsByUserId(String userId);

    // 중복 휴대폰 번호 검사
    boolean existsByPhone(String phone);
}
