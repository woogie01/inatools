package inatools.backend.dto.userconnection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserCareConnectionRequest(

        @Schema(description = "연결 요청을 받은 회원 아이디", example = "2")
        @NotEmpty String requestedUserId,

        @Schema(description = "본인 식별자", example = "1")
        @NotNull Long memberId
) {

}
