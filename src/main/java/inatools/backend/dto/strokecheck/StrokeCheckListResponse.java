package inatools.backend.dto.strokecheck;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record StrokeCheckListResponse(
        @Schema(description = "뇌졸중 검사 결과 목록")
        List<StrokeCheckResponse> strokeCheckResponseList
) {

}
