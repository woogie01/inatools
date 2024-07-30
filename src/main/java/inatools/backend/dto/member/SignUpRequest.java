package inatools.backend.dto.member;

import inatools.backend.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record SignUpRequest (

        @Schema(description = "회원명", example = "pilho")
        @NotEmpty String username,

        @Schema(description = "아이디", example = "pil0602")
        @NotEmpty String userId,

        @Schema(description = "비밀번호", example = "010602")
        @NotEmpty String password,

        @Schema(description = "이메일", example = "pil0602@naver.com")
        @NotEmpty String email,

        @Schema(description = "전화번호", example = "01012345678")
        @NotEmpty String phone
) {

    public static SignUpRequest fromMember(Member member) {
        return new SignUpRequest (
                member.getUsername(),
                member.getUserId(),
                member.getPassword().getValue(),
                member.getEmail(),
                member.getPhone()
        );
    }
}
