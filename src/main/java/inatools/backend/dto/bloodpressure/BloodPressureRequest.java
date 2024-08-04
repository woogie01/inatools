package inatools.backend.dto.bloodpressure;

public record BloodPressureRequest(
        Long memberId,
        Long measurementNumber,
        Long systolicPressure,
        Long diastolicPressure
) {

}
