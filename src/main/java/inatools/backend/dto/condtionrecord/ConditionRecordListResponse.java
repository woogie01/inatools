package inatools.backend.dto.condtionrecord;

import java.util.List;

public record ConditionRecordListResponse(
        List<ConditionRecordResponse> conditionRecordList
) {

}
