package inatools.backend.dto.condtiondetails;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record ConditionDetailsListResponse(

        @Schema(description = "몸 상세 기록 목록")
        List<ConditionDetailsRecordResponse> conditionDetailsRecordList
) {

}
