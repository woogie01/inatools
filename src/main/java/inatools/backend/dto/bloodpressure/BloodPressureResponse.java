package inatools.backend.dto.bloodpressure;

import inatools.backend.domain.BloodPressure;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record BloodPressureResponse(

        @Schema(description = "혈압 측정 기록 식별자", example = "1")
        Long bloodPressureId,

        @Schema(description = "측정 일시", example = "2024-08-11T00:00:00")
        LocalDateTime recordAt,

        @Schema(description = "측정 회차", example = "1")
        Long measurementNumber,

        @Schema(description = "수축기 혈압", example = "110")
        Long systolicPressure,

        @Schema(description = "이완기 혈압", example = "70")
        Long diastolicPressure
) {

    public static BloodPressureResponse fromBloodPressure(BloodPressure bloodPressure) {
        return new BloodPressureResponse(
                bloodPressure.getId(),
                bloodPressure.getRecordAt(),
                bloodPressure.getRecordNumber(),
                bloodPressure.getSystolicPressure(),
                bloodPressure.getDiastolicPressure()
        );
    }
}
