package inatools.backend.repository;

import inatools.backend.domain.MedicationInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationInfoRepository extends JpaRepository<MedicationInfo, Long> {

    List<MedicationInfo> findAllByMemberId(Long memberId);

}
