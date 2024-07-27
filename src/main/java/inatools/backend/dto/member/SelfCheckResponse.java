package inatools.backend.dto.member;

import inatools.backend.domain.DrinkingStatus;
import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.Member;
import inatools.backend.domain.SmokingStatus;
import java.util.List;

public record SelfCheckResponse(
        long gender,
        long age,
        String underlyingDisease, // 기저질환
        boolean familyHistory, // 가족력
        SmokingStatus smokingStatus, // 흡연
        DrinkingStatus drinkingStatus, // 음주
        List<MedicationInfo> medicationInfoList // 복용약
) {

    public static SelfCheckResponse fromMember(Member member, List<MedicationInfo> medicationInfoList) {
        return new SelfCheckResponse(
                member.getGender(),
                member.getAge(),
                member.getUnderlyingDisease(),
                member.isFamilyHistory(),
                member.getSmokingStatus(),
                member.getDrinkingStatus(),
                medicationInfoList
                );
    }
}
