package inatools.backend.repository;

import inatools.backend.domain.ConditionRecord;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRecordRepository extends JpaRepository<ConditionRecord, Long> {

    List<ConditionRecord> findAllByMemberIdAndRecordAtBetween(Long memberId, LocalDate startDate, LocalDate endDate);
}
