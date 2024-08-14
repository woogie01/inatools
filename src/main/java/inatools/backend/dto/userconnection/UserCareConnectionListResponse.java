package inatools.backend.dto.userconnection;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record UserCareConnectionListResponse(

        @Schema(description = "친구 목록")
        List<UserCareConnectionResponse> userCareConnectionList
) {

}
