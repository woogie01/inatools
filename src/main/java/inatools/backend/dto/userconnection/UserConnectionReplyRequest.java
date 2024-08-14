package inatools.backend.dto.userconnection;

import inatools.backend.domain.ConnectionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UserConnectionReplyRequest(

        @Schema(description = "응답 유형", example = "ACCEPTED")
        @NotNull ConnectionStatus replyStatus,

        @Schema(description = "연결 요청을 받은 회원 식별자", example = "2")
        @NotNull Long requestedMemberId,

        @Schema(description = "연결을 요청한 회원 식별자", example = "1")
        @NotNull Long requestingMemberId
) {
}
