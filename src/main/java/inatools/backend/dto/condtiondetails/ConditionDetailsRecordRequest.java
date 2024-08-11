package inatools.backend.dto.condtiondetails;

import inatools.backend.domain.CommonCondition;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record ConditionDetailsRecordRequest(

        @NotNull
        @Schema(description = "기록 날짜", example = "2024-08-11")
        LocalDate recordAt,

        @NotNull
        @Schema(description = "몸 상태", example = "[\"DIZZINESS\", \"WEAKNESS\"]")
        List<CommonCondition> commonConditionList,

        @NotNull
        @Schema(description = "몸 상태", example = "그냥저냥")
        String conditionDetails,

        @NotNull
        @Schema(description = "회원 식별자", example = "1")
        Long memberId
) {

}
