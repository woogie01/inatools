package inatools.backend.repository;

import inatools.backend.domain.ConditionDetailsRecord;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionDetailsRecordRepository extends JpaRepository<ConditionDetailsRecord, Long> {

    Optional<ConditionDetailsRecord> findByMemberIdAndRecordDate(Long memberId, LocalDate recordDate);

    List<ConditionDetailsRecord> findAllByMemberIdAndRecordDate(Long memberId, LocalDate recordDate);
}
