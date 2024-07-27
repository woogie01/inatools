package inatools.backend.dto.medication;

import inatools.backend.domain.MedicationInfo;

public record MedicationInfoResponse(
        String medicationName, // 약 이름
        Long dosage // 복용량
) {

    public static MedicationInfoResponse fromMedicationInfo(MedicationInfo medicationInfo) {
        return new MedicationInfoResponse(
                medicationInfo.getMedicationName(),
                medicationInfo.getDosage()
        );
    }
}
