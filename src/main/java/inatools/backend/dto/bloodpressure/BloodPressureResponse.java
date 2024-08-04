package inatools.backend.dto.bloodpressure;

import inatools.backend.domain.BloodPressure;
import java.time.LocalDateTime;

public record BloodPressureResponse(
        Long bloodPressureId,
        LocalDateTime measureDate,
        Long measurementNumber,
        Long systolicPressure,
        Long diastolicPressure,
        Long memberId
) {

    public static BloodPressureResponse fromBloodPressure(BloodPressure bloodPressure) {
        return new BloodPressureResponse(
                bloodPressure.getId(),
                bloodPressure.getMeasureDateTime(),
                bloodPressure.getMeasurementNumber(),
                bloodPressure.getSystolicPressure(),
                bloodPressure.getDiastolicPressure(),
                bloodPressure.getMember().getId()
        );
    }
}
