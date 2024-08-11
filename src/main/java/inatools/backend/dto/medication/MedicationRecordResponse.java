package inatools.backend.dto.medication;

import inatools.backend.domain.MedicationRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

public record MedicationRecordResponse(

        @Schema(description = "복약 기록 식별자", example = "1")
        Long id,

        @Schema(description = "복약 여부", example = "true")
        boolean isTaken,

        @Schema(description = "기록 날짜", example = "2024-08-10")
        LocalDate recordDate,

        @Schema(description = "복용약 정보")
        MedicationInfoResponse medicationInfo
) {

    public static MedicationRecordResponse fromMedicationRecord(MedicationRecord medicationRecord) {
        return new MedicationRecordResponse(
                medicationRecord.getId(),
                medicationRecord.isTaken(),
                medicationRecord.getRecordAt(),
                MedicationInfoResponse.fromMedicationInfo(medicationRecord.getMedicationInfo())
        );
    }
}
