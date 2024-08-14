package inatools.backend.dto.userconnection;

import inatools.backend.domain.DangerStatus;
import inatools.backend.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

public record ConnectionMemberInfoResponse(

        @Schema(description = "회원 식별자", example = "1")
        Long memberId,

        @Schema(description = "회원 이름", example = "신민규")
        String userName,

        @Schema(description = "생년월일", example = "2001-10-16")
        LocalDate birthDate,

        @Schema(description = "뇌졸중 위험군", example = "안전")
        DangerStatus dangerStatus

) {
    public static ConnectionMemberInfoResponse fromMember(Member member) {
        return new ConnectionMemberInfoResponse(
                member.getId(),
                member.getUsername(),
                member.getBirthDate(),
                member.getDangerStatus()
        );
    }
}
