package inatools.backend.dto.medication;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record MedicationInfoListResponse(
        @Schema(description = "복용 약 정보 목록")
        List<MedicationInfoResponse> medicationInfoList
) {
}
