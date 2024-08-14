package inatools.backend.dto.userconnection;

import inatools.backend.domain.ConnectionStatus;
import inatools.backend.domain.UserCareConnection;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserCareConnectionResponse(

        @Schema(description = "연결 요청 식별자", example = "1")
        Long id,

        @Schema(description = "요청 상태", example = "REQUESTING")
        ConnectionStatus connectionStatus,

        @Schema(description = "연결 요청을 받은 회원 식별자", example = "2")
        Long requestedMemberId,

        @Schema(description = "연결을 요청한 회원 식별자", example = "1")
        Long requestingMemberId

) {

    public static UserCareConnectionResponse fromUserCareConnection(UserCareConnection userCareConnection) {
        return new UserCareConnectionResponse(
                userCareConnection.getId(),
                userCareConnection.getConnectionStatus(),
                userCareConnection.getRequestedMember().getId(),
                userCareConnection.getRequestingMember().getId()
        );
    }
}
