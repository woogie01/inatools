package inatools.backend.dto.medication;

import inatools.backend.domain.MedicationInfo;

public record MedicationInfoResponse(
        Long id,
        String medicationName, // 약 이름
        Long dosage // 복용량
) {

    public static MedicationInfoResponse fromMedicationInfo(MedicationInfo medicationInfo) {
        return new MedicationInfoResponse(
                medicationInfo.getId(),
                medicationInfo.getMedicationName(),
                medicationInfo.getDosage()
        );
    }
}
