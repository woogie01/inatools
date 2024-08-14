package inatools.backend.dto.carereceiver;

import inatools.backend.domain.UserCareConnection;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserCareConnectionResponse(

        @Schema(description = "연결 요청 식별자", example = "1")
        Long id,

        @Schema(description = "연결 요청을 받은 회원 식별자", example = "2")
        Long responseMemberId,

        @Schema(description = "연결을 요청한 회원 식별자", example = "1")
        Long requestMemberId

) {

    public static UserCareConnectionResponse fromCareReceiver(UserCareConnection userCareConnection) {
        return new UserCareConnectionResponse(
                userCareConnection.getId(),
                userCareConnection.getConnectedMemberId(),
                userCareConnection.getMember().getId()
        );
    }
}
