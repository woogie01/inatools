package inatools.backend.dto.bloodpressure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record BloodPressureRequest(

        @NotNull
        @Schema(description = "회원 식별자", example = "1")
        Long memberId,

        @NotNull
        @Schema(description = "측정 날짜", example = "2024-08-11T00:00:00")
        LocalDateTime recordAt,

        @NotNull(message = "측정 회차를 입력해주세요.")
        @Schema(description = "측정 회차", example = "1")
        Long recordNumber,

        @NotNull(message = "수축기 혈압을 입력해주세요.")
        @Schema(description = "수축기 혈압", example = "110")
        Long systolicPressure,

        @NotNull(message = "이완기 혈압을 입력해주세요.")
        @Schema(description = "이완기 혈압", example = "70")
        Long diastolicPressure
) {

}