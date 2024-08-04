package inatools.backend.dto.bloodpressure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BloodPressureRequest(

        @NotNull
        @Schema(description = "회원 식별자", example = "1")
        Long memberId,

        @NotNull
        @Schema(description = "측정 회차", example = "1")
        Long measurementNumber,

        @NotNull
        @Schema(description = "수축기 혈압", example = "110")
        Long systolicPressure,

        @NotNull
        @Schema(description = "이완기 혈압", example = "70")
        Long diastolicPressure
) {

}
