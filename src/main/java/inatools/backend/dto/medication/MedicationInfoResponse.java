package inatools.backend.dto.medication;

import inatools.backend.domain.MedicationInfo;
import io.swagger.v3.oas.annotations.media.Schema;

public record MedicationInfoResponse(

        @Schema(description = "복용 중인 약 정보 식별자", example = "1")
        Long id,

        @Schema(description = "약 이름", example = "혈압약")
        String medicationName, // 약 이름

        @Schema(description = "하루 복용량", example = "2")
        Long dosage, // 복용량

        @Schema(description = "현재 복용 여부", example = "true")
        boolean active // 활성화 여부
) {

    public static MedicationInfoResponse fromMedicationInfo(MedicationInfo medicationInfo) {
        return new MedicationInfoResponse(
                medicationInfo.getId(),
                medicationInfo.getMedicationName(),
                medicationInfo.getDosage(),
                medicationInfo.isActive()
        );
    }

}
