package inatools.backend.repository;

import inatools.backend.domain.MedicationRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRecordRepository extends JpaRepository<MedicationRecord, Long> {

    List<MedicationRecord> findAllByMemberIdAndIsActive(Long memberId, boolean isActive);

}
