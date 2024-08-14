package inatools.backend.repository;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import inatools.backend.domain.ConnectionStatus;
import inatools.backend.domain.Member;
import inatools.backend.domain.UserCareConnection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConnectionRepository extends JpaRepository<UserCareConnection, Long> {

    List<UserCareConnection> findAllByRequestedMemberOrRequestingMember(Member requestedMember, Member requestingMember);

    Optional<UserCareConnection> findByRequestedMemberAndRequestingMember(Member requestedMember, Member requestingMember);

    Optional<UserCareConnection> findByRequestedMemberAndRequestingMemberAndConnectionStatus(Member requestedMember, Member requestingMember, ConnectionStatus connectionStatus);
}
