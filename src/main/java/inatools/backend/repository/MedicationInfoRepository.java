package inatools.backend.repository;

import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationInfoRepository extends JpaRepository<MedicationInfo, Long> {

    List<MedicationInfo> findAllByMember(Member member);

}
