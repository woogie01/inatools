package inatools.backend.dto;

import inatools.backend.domain.Member;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record SignUpRequest (
        Long id,
        @NotEmpty String name,
        @NotEmpty String password,
        @Min(0) @Max(1) long gender,
        @Min(0) long age,
        @NotEmpty String phone,
        String underlyingDisease, // 기저질환
        boolean familyHistory, // 가족력
        boolean isSmoker, // 흡연
        boolean isDrinker, // 음주
        String medication // 복용약
) {

    public static SignUpRequest fromMember(Member member) {
        return new SignUpRequest (
                member.getId(),
                member.getName(),
                member.getPassword(),
                member.getGender(),
                member.getAge(),
                member.getPhone(),
                member.getUnderlyingDisease(),
                member.isFamilyHistory(),
                member.isSmoker(),
                member.isDrinker(),
                member.getMedication()
        );
    }
}
