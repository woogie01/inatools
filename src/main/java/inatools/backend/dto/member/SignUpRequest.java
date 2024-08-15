package inatools.backend.dto.member;

import inatools.backend.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SignUpRequest (

        @Schema(description = "회원명", example = "pilho")
        @NotEmpty String username,

        @Schema(description = "아이디", example = "pil0602")
        @Size(min = 5, max = 20, message = "아이디는 5자 이상, 20자 이하여야 합니다.")
        String userId,

        @Schema(description = "비밀번호", example = "010602")
        @Size(min = 6, max = 12, message = "비밀번호는 6자 이상, 12자 이하여야 합니다.")
        String password,

        @Schema(description = "이메일", example = "pil0602@naver.com")
        @NotEmpty String email,

        @Schema(description = "전화번호", example = "01012345678")
        @Pattern(regexp = "\\d{10,11}", message = "전화번호는 10~11자리 숫자여야 합니다.")
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
