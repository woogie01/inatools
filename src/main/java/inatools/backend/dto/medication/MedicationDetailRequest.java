package inatools.backend.dto.medication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicationDetailRequest(

        @NotBlank(message = "현재 복용 중인 약 이름을 입력해주세요.")
        @Schema(description = "약 이름", example = "혈압약")
        String medicationName, // 약 이름

        @NotNull(message = "하루 복용량을 입력해주세요.")
        @Schema(description = "하루 복용량", example = "2")
        Long dosage // 복용량
) {
}
