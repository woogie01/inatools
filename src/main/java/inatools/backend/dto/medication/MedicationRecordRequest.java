package inatools.backend.dto.medication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record MedicationRecordRequest(

        @Schema(description = "복약 여부", example = "true")
        boolean isTaken,

        @NotNull
        @Schema(description = "기록 날짜", example = "2024-08-10")
        LocalDate recordDate,

        @NotNull
        @Schema(description = "복용약 정보 식별자", example = "1")
        Long medicationInfoId
) {

}
