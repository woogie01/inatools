package inatools.backend.dto.bloodpressure;

import java.time.LocalDate;

public record BloodPressureRequest(
        Long memberId,
        Long measurementNumber,
        Long systolicPressure,
        Long diastolicPressure
) {

}
