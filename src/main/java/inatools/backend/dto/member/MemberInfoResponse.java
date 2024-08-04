package inatools.backend.dto.member;

import inatools.backend.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberInfoResponse(

        @Schema(description = "회원 식별자", example = "1")
        Long id,

        @Schema(description = "회원 이름", example = "홍길동")
        String name,

        @Schema(description = "회원 ID", example = "hong123")
        String userId,

        @Schema(description = "회원 이메일", example = "pil0602@naver.com")
        String email,

        @Schema(description = "회원 전화번호", example = "01012345678")
        String phone
) {
    public static MemberInfoResponse fromMember(Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getUsername(),
                member.getUserId(),
                member.getEmail(),
                member.getPhone()
        );
    }
}
