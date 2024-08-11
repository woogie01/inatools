package inatools.backend.dto.condtiondetails;

import inatools.backend.domain.CommonCondition;
import inatools.backend.domain.ConditionDetailsRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;

public record ConditionDetailsRecordResponse(

        @Schema(description = "몸 상태 기록 식별자", example = "1")
        Long conditionDetailsRecordId,

        @Schema(description = "몸 상태 유형", example = "WEAKNESS")
        List<CommonCondition> commonCondition,

        @Schema(description = "기록 날짜", example = "2024-08-15")
        LocalDate recordAt,

        @Schema(description = "몸 상태", example = "그냥저냥")
        String conditionDetails

) {
        public static ConditionDetailsRecordResponse fromConditionDetailsRecord(ConditionDetailsRecord conditionDetailsRecord) {
                return new ConditionDetailsRecordResponse(
                        conditionDetailsRecord.getId(),
                        conditionDetailsRecord.getCommonConditionList(),
                        conditionDetailsRecord.getRecordAt(),
                        conditionDetailsRecord.getConditionDetails()
                );
        }
}
