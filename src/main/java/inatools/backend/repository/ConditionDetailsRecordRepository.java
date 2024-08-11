package inatools.backend.repository;

import inatools.backend.domain.ConditionDetailsRecord;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionDetailsRecordRepository extends JpaRepository<ConditionDetailsRecord, Long> {

    Optional<ConditionDetailsRecord> findByMemberIdAndRecordDate(Long memberId, LocalDate recordDate);
}
