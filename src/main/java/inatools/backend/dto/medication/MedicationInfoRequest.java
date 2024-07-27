package inatools.backend.dto.medication;

public record MedicationInfoRequest(
        String medicationName, // 약 이름
        Long dosage // 복용량
) {

}
