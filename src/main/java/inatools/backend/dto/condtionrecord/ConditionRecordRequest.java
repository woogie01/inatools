package inatools.backend.dto.condtionrecord;


import inatools.backend.domain.ConditionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ConditionRecordRequest(

        @NotNull
        @Schema(description = "컨디션 상태", example = "GOOD")
        ConditionType conditionType,

        @NotNull
        @Schema(description = "기록 날짜", example = "2024-08-11")
        LocalDate recordAt,

        @NotNull
        @Schema(description = "회원 식별자", example = "1")
        Long memberId

) {

}
