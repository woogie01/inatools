package inatools.backend.dto.condtiondetails;

import inatools.backend.domain.ConditionDetailsRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

public record ConditionDetailsRecordResponse(

        @Schema(description = "몸 상태 기록 식별자", example = "1")
        Long conditionDetailsRecordId,

        @Schema(description = "기록 날짜", example = "2024-08-15")
        LocalDate recordDate,

        @Schema(description = "몸 상태", example = "좋음")
        String conditionDetails

) {
        public static ConditionDetailsRecordResponse fromConditionDetailsRecord(ConditionDetailsRecord conditionDetailsRecord) {
                return new ConditionDetailsRecordResponse(
                        conditionDetailsRecord.getId(),
                        conditionDetailsRecord.getRecordDate(),
                        conditionDetailsRecord.getConditionDetails()
                );
        }
}
