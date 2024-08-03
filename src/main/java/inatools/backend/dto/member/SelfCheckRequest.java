package inatools.backend.dto.member;

import inatools.backend.domain.DrinkingStatus;
import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.MedicationRecord;
import inatools.backend.domain.SmokingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

public record SelfCheckRequest(
        @Min(0) @Max(1) long gender,
        LocalDate birthDate,
        String underlyingDisease, // 기저질환
        boolean familyHistory, // 가족력
        SmokingStatus smokingStatus, // 흡연
        DrinkingStatus drinkingStatus, // 음주
        List<MedicationInfo> medicationInfoList // 복용약
) {

}
