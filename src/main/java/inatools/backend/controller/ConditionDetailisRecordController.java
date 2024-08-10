package inatools.backend.controller;

import inatools.backend.domain.ConditionDetailsRecord;
import inatools.backend.dto.condtiondetails.ConditionDetailsRecordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "몸 상태 기록 관련 로직", description = "몸 상태 기록 API")
@RestController
@RequestMapping("/api/condition-detailis-record")
@RequiredArgsConstructor
public class ConditionDetailisRecordController {

    /**
     * 몸 상태 기록 API
     */
    @Operation(summary = "몸 상태 기록", description = "몸 상태를 기록하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<ConditionDetailsRecordResponse> create(
            @RequestBody @Valid ConditionDetailsRecordRequest conditionDetailsRecordRequest, Principal principal) {
        String loginId = principal.getName();
        ConditionDetailsRecord conditionDetailsRecord = conditionDetailsRecordService.createConditionDetailsRecord(loginId, conditionDetailsRecordRequest);
        ConditionDetailsRecordResponse response = ConditionDetailsRecordResponse.fromConditionDetailsRecord(conditionDetailsRecord);
        return ResponseEntity.ok(response);
    }

}
