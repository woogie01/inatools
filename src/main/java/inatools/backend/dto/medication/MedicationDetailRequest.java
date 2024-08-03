package inatools.backend.dto.medication;

public record MedicationDetailRequest(
        String medicationName, // 약 이름
        Long dosage // 복용량
) {
}
