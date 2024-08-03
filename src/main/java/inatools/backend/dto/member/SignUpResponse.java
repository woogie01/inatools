package inatools.backend.dto.member;

import inatools.backend.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpResponse(

        @Schema(description = "user index", example = "1")
        Long id,

        @Schema(description = "username", example = "pilho")
        String username,

        @Schema(description = "userId", example = "pil0602")
        String userId,

        @Schema(description = "비밀번호", example = "010602")
        String password,

        @Schema(description = "이메일", example = "pil0602@naver.com")
        String email,

        @Schema(description = "전화번호", example = "01012345678")
        String phone,

        @Schema(description = "Access Token", example = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NSwiaWF0Ijox123456wMDM5LCJleHAiOjE3MDAyNzIwMzl9.0mjwFO6JtabcdefgrPgGm-2XWY60G2YH6HbTwU386Po")
        String accessToken,

        @Schema(description = "Refresh Token", example = "eyABCDciOiJIUzI1NiJ9.abcdefg6NSwiaWF0IjoxNjk3NjgwMDM5LCJleHAiOjE3Mxyz987wMzl9.0mjwFO6Jtqz616yprPgGm-2XWY60G2YH6HbTwU123q0")
        String refreshToken

) {
    public static SignUpResponse fromMember(Member member, String accessToken, String refreshToken) {
        return new SignUpResponse(
                member.getId(),
                member.getUsername(),
                member.getUserId(),
                member.getPassword().getValue(),
                member.getEmail(),
                member.getPhone(),
                accessToken,
                refreshToken
        );
    }
}
