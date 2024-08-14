package inatools.backend.dto.member;

import inatools.backend.domain.DangerStatus;
import inatools.backend.domain.DrinkingStatus;
import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.Member;
import inatools.backend.domain.SmokingStatus;
import inatools.backend.dto.medication.MedicationInfoRequest;
import inatools.backend.dto.medication.MedicationInfoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record SelfCheckResponse(

        @Schema(description = "성별", example = "1")
        long gender,

        @Schema(description = "생년월일", example = "1996-02-06")
        LocalDate birthDate,

        @Schema(description = "기저질환", example = "고혈압")
        String underlyingDisease, // 기저질환

        @Schema(description = "가족력", example = "true")
        boolean familyHistory, // 가족력

        @Schema(description = "흡연량", example = "NON_SMOKER")
        SmokingStatus smokingStatus, // 흡연

        @Schema(description = "음주 주기", example = "NON_DRINKER")
        DrinkingStatus drinkingStatus, // 음주

        @Schema(description = "뇌졸중 위험군", example = "SAFE")
        DangerStatus dangerStatus // 뇌졸중 위험군
) {

    public static SelfCheckResponse fromMember(Member member) {
        return new SelfCheckResponse(
                member.getGender(),
                member.getBirthDate(),
                member.getUnderlyingDisease(),
                member.isFamilyHistory(),
                member.getSmokingStatus(),
                member.getDrinkingStatus(),
                member.getDangerStatus()
        );
    }
}
