package inatools.backend.repository;

import inatools.backend.domain.Member;
import inatools.backend.domain.UserCareConnection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConnectionRepository extends JpaRepository<UserCareConnection, Long> {

    List<UserCareConnection> findAllByRequestedMemberOrRequestingMember(Member requestedMember, Member requestingMember);

    Optional<UserCareConnection> findByRequestedMemberAndRequestingMember(Member requestedMember, Member requestingMember);
}
