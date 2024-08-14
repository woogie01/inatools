package inatools.backend.dto.userconnection;

import inatools.backend.domain.ConnectionStatus;
import inatools.backend.domain.UserCareConnection;
import inatools.backend.dto.member.MemberInfoResponse;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserCareConnectionResponse(

        @Schema(description = "연결 요청 식별자", example = "1")
        Long id,

        @Schema(description = "요청 상태", example = "REQUESTING")
        ConnectionStatus connectionStatus,

        @Schema(description = "연결 요청을 받은 회원", example = "{\n"
                + "    \"memberId\": 2,\n"
                + "    \"userName\": \"pil\",\n"
                + "    \"birthDate\": null,\n"
                + "    \"dangerStatus\": null\n"
                + "  }")
        ConnectionMemberInfoResponse requestedMember

) {

    public static UserCareConnectionResponse fromUserCareConnection(UserCareConnection userCareConnection) {
        return new UserCareConnectionResponse(
                userCareConnection.getId(),
                userCareConnection.getConnectionStatus(),
                ConnectionMemberInfoResponse.fromMember(userCareConnection.getRequestedMember())
        );
    }
}
