package inatools.backend.dto.carereceiver;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UserCareConnectionRequest(

        @Schema(description = "연결된 회원 식별자", example = "2")
        @NotNull Long connectedMemberId,

        @Schema(description = "본인 식별자", example = "1")
        @NotNull Long memberId
) {

}
