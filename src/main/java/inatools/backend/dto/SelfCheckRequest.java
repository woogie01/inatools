package inatools.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record SelfCheckRequest(
        @Min(0) @Max(1) long gender,
        @Min(0) long age,
        String underlyingDisease, // 기저질환
        boolean familyHistory, // 가족력
        boolean isSmoker, // 흡연
        boolean isDrinker, // 음주
        String medication // 복용약
) {

}
