package inatools.backend.dto.medication;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record MedicationRecordListResponse(
        @Schema(description = "복용 약 기록 목록")
        List<MedicationRecordResponse> medicationRecordList
) {

}
