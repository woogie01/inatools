package inatools.backend.repository;

import inatools.backend.domain.UserCareConnection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConnectionRepository extends JpaRepository<UserCareConnection, Long> {

    List<UserCareConnection> findAllByMemberId(Long memberId);
}
