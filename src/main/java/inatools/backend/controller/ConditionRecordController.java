package inatools.backend.controller;

import inatools.backend.domain.ConditionRecord;
import inatools.backend.dto.condtionrecord.ConditionRecordRequest;
import inatools.backend.dto.condtionrecord.ConditionRecordResponse;
import inatools.backend.service.ConditionRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "컨디션 기록 관련 로직", description = "컨디션 기록 API")
@RestController
@RequestMapping("/api/condition-record")
@RequiredArgsConstructor
public class ConditionRecordController {

    private final ConditionRecordService conditionRecordService;

    /**
     * 컨디션 기록 API
     */
    @Operation(summary = "컨디션 상태 기록", description = "컨디션 상태를 기록하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<ConditionRecordResponse> create(
            @RequestBody @Valid ConditionRecordRequest conditionRecordRequest, Principal principal) {
        String loginId = principal.getName();
        ConditionRecord conditionRecord = conditionRecordService.createConditionRecord(loginId, conditionRecordRequest);
        ConditionRecordResponse response = ConditionRecordResponse.fromConditionRecord(conditionRecord);
        return ResponseEntity.ok(response);
    }

    /**
     * 컨디션 기록 수정 API
     */
    @Operation(summary = "컨디션 상태 기록 수정", description = "컨디션 상태를 수정하기 위한 API입니다.")
    @PostMapping("/{id}")
    public ResponseEntity<ConditionRecordResponse> update(
            @PathVariable("id") Long conditionRecordId,
            @RequestBody @Valid ConditionRecordRequest conditionRecordRequest,
            Principal principal) {
        String loginId = principal.getName();
        ConditionRecord conditionRecord =
                conditionRecordService.updateConditionRecord(conditionRecordId, loginId, conditionRecordRequest);
        ConditionRecordResponse response = ConditionRecordResponse.fromConditionRecord(conditionRecord);
        return ResponseEntity.ok(response);
    }

}
