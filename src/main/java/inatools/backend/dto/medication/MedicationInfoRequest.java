package inatools.backend.dto.medication;

public record MedicationInfoRequest(
        Long memberId, // 회원 ID
        String medicationName, // 약 이름
        Long dosage // 복용량
) {

}
