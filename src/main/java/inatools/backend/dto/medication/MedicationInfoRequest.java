package inatools.backend.dto.medication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record MedicationInfoRequest(

        @NotNull
        @Schema(description = "회원 식별자", example = "1")
        Long memberId, // 회원 ID

        @NotNull
        @Schema(example = "[{\"medicationName\": \"혈압약\", \"dosage\": 2}]")
        List<MedicationDetailRequest> medications // 복용약 정보 리스트
) {

}
