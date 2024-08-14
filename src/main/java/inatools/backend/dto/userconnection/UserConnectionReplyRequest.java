package inatools.backend.dto.userconnection;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserConnectionReplyRequest(

        @Schema(description = "연결 요청 식별자", example = "1")
        Long id,


) {
}
