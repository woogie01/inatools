package inatools.backend.dto.condtiondetails;

import inatools.backend.domain.CommonConditionDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ConditionDetailsRecordRequest(

        @NotNull
        @Schema(description = "몸 상태", example = "WEAKNESS")
        CommonConditionDetails commonConditionDetails,

        @NotNull
        @Schema(description = "몸 상태", example = "그냥저냥")
        String conditionDetails,

        @NotNull
        @Schema(description = "회원 식별자", example = "1")
        Long memberId
) {

}
