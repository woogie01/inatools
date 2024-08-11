package inatools.backend.repository;

import inatools.backend.domain.ConditionRecord;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRecordRepository extends JpaRepository<ConditionRecord, Long> {

    Optional<ConditionRecord> findByMemberIdAndRecordDate(Long memberId, LocalDate recordDate);
}
