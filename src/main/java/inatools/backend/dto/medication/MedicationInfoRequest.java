package inatools.backend.dto.medication;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record MedicationInfoRequest(
        @NotNull Long memberId, // 회원 ID
        List<MedicationDetailRequest> medications // 복용약 정보 리스트
) {

}
