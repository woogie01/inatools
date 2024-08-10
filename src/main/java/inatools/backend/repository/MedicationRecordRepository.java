package inatools.backend.repository;

import inatools.backend.domain.MedicationRecord;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRecordRepository extends JpaRepository<MedicationRecord, Long> {

    List<MedicationRecord> findAllByMedicationInfoIdInAndRecordAtBetween(List<Long> medicationInfoIds, LocalDate startDate, LocalDate endDate);
}
