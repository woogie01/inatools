package inatools.backend.dto.member;

import inatools.backend.domain.DangerStatus;
import inatools.backend.domain.DrinkingStatus;
import inatools.backend.domain.SmokingStatus;
import inatools.backend.dto.medication.MedicationInfoRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record SelfCheckRequest(

        @Min(0) @Max(1)
        @Schema(description = "성별", example = "0")
        Long gender,

        @NotNull(message = "생년월일을 입력해주세요.")
        @Schema(description = "생년월일", example = "2001-01-01")
        LocalDate birthDate,

        @NotBlank(message = "기저질환을 입력해주세요.")
        @Schema(description = "기저질환", example = "고혈압")
        String underlyingDisease, // 기저질환

        @Schema(description = "가족력", example = "true")
        boolean familyHistory, // 가족력

        @NotNull(message = "흡연량을 입력해주세요.")
        @Schema(description = "흡연량", example = "NON_SMOKER")
        SmokingStatus smokingStatus, // 흡연

        @NotNull(message = "음주 주기를 입력해주세요.")
        @Schema(description = "음주 주기", example = "NON_DRINKER")
        DrinkingStatus drinkingStatus, // 음주

        @NotNull(message = "뇌졸중 위험군을 입력해주세요.")
        @Schema(description = "뇌졸중 위험군", example = "SAFE")
        DangerStatus dangerStatus // 뇌졸중 위험군
) {

}
