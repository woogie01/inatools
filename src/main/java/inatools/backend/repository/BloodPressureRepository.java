package inatools.backend.repository;

import inatools.backend.domain.BloodPressure;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {

    List<BloodPressure> findAllByMemberId(Long memberId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
