package inatools.backend.dto;

import inatools.backend.domain.Member;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record SignUpRequest (
        @NotEmpty String username,
        @NotEmpty String userId,
        @NotEmpty String password,
        @NotEmpty String email,
        @NotEmpty String phone
) {

    public static SignUpRequest fromMember(Member member) {
        return new SignUpRequest (
                member.getUsername(),
                member.getUserId(),
                member.getPassword(),
                member.getEmail(),
                member.getPhone()
        );
    }
}
