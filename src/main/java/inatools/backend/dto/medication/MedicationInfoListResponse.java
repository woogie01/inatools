package inatools.backend.dto.medication;

import java.util.List;

public record MedicationInfoListResponse(
        List<MedicationInfoResponse> medicationInfoList
) {
}
