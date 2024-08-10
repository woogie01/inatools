package inatools.backend.controller;

import inatools.backend.domain.ConditionDetailsRecord;
import inatools.backend.dto.condtiondetails.ConditionDetailsRecordRequest;
import inatools.backend.dto.condtiondetails.ConditionDetailsRecordResponse;
import inatools.backend.service.ConditionDetailsRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "몸 상태 기록 관련 로직", description = "몸 상태 기록 API")
@RestController
@RequestMapping("/api/condition-detailis-record")
@RequiredArgsConstructor
public class ConditionDetailisRecordController {

    private final ConditionDetailsRecordService conditionDetailsRecordService;

    /**
     * 몸 상태 기록 API
     */
    @Operation(summary = "몸 상태 기록", description = "몸 상태를 기록하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<ConditionDetailsRecordResponse> create(
            @RequestBody @Valid ConditionDetailsRecordRequest conditionDetailsRecordRequest,
            Principal principal) {
        String loginId = principal.getName();
        ConditionDetailsRecord conditionDetailsRecord =
                conditionDetailsRecordService.createConditionDetailsRecord(loginId, conditionDetailsRecordRequest);
        ConditionDetailsRecordResponse response =
                ConditionDetailsRecordResponse.fromConditionDetailsRecord(conditionDetailsRecord);
        return ResponseEntity.ok(response);
    }

    /**
     * 몸 상태 기록 수정 API
     */
    @Operation(summary = "몸 상태 기록 수정", description = "몸 상태 기록을 수정하기 위한 API입니다.")
    @PostMapping("/{id}")
    public ResponseEntity<ConditionDetailsRecordResponse> update(
            @PathVariable("id") Long conditionDetailsRecordId,
            @RequestBody @Valid ConditionDetailsRecordRequest conditionDetailsRecordRequest, Principal principal) {
        String loginId = principal.getName();
        ConditionDetailsRecord conditionDetailsRecord =
                conditionDetailsRecordService.updateConditionDetailsRecord(conditionDetailsRecordId, loginId,
                        conditionDetailsRecordRequest);
        ConditionDetailsRecordResponse response =
                ConditionDetailsRecordResponse.fromConditionDetailsRecord(conditionDetailsRecord);
        return ResponseEntity.ok(response);
    }


}
