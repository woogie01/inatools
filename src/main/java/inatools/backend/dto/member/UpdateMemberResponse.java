package inatools.backend.dto.member;

import inatools.backend.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateMemberResponse(

        @Schema(description = "회원 식별자", example = "1")
        Long id,

        @Schema(description = "회원명", example = "pilhosss")
        String name,

        @Schema(description = "아이디", example = "pil1016")
        String userId,

        @Schema(description = "비밀번호", example = "011016")
        String password,

        @Schema(description = "휴대폰 번호", example = "01012345555")
        String phone
) {
    public static UpdateMemberResponse fromMember(Member member) {
        return new UpdateMemberResponse(
                member.getId(),
                member.getUsername(),
                member.getUserId(),
                member.getPassword().getValue(),
                member.getPhone()
        );
    }
}
