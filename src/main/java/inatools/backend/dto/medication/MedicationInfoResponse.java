package inatools.backend.dto.medication;

import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.Member;
import java.util.List;

public record MedicationInfoResponse(
        Long id,
        String medicationName, // 약 이름
        Long dosage, // 복용량
        boolean isActive // 활성화 여부
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
