package inatools.backend.repository;

import inatools.backend.domain.BloodPressure;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {

    List<BloodPressure> findAllByMemberIdAndRecordAtBetween(Long memberId, LocalDate startDate, LocalDate endDate);
}
