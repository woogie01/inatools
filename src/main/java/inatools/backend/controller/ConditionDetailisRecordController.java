package inatools.backend.controller;

import inatools.backend.domain.ConditionDetailsRecord;
import inatools.backend.dto.condtiondetails.ConditionDetailsRecordRequest;
import inatools.backend.dto.condtiondetails.ConditionDetailsRecordResponse;
import inatools.backend.service.ConditionDetailsRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "몸 상태 기록 관련 로직", description = "몸 상태 기록 API")
@RestController
@RequestMapping("/api/condition-details-record")
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
            @RequestBody @Valid ConditionDetailsRecordRequest conditionDetailsRecordRequest,
            Principal principal) {
        String loginId = principal.getName();
        ConditionDetailsRecord conditionDetailsRecord =
                conditionDetailsRecordService.updateConditionDetailsRecord(conditionDetailsRecordId, loginId,
                        conditionDetailsRecordRequest);
        ConditionDetailsRecordResponse response =
                ConditionDetailsRecordResponse.fromConditionDetailsRecord(conditionDetailsRecord);
        return ResponseEntity.ok(response);
    }

    /**
     * 몸 상태 기록 조회 API
     */
    @Operation(summary = "몸 상태 기록 조회", description = "몸 상태 기록을 조회하기 위한 API입니다.")
    @GetMapping("/members/{id}")
    public ResponseEntity<ConditionDetailsRecordResponse> get(
            @PathVariable("id") Long memberId,
            @RequestParam LocalDate recordDate,
            Principal principal) {
        String loginId = principal.getName();
        ConditionDetailsRecord conditionDetailsRecord =
                conditionDetailsRecordService.getConditionDetailsRecord(loginId, memberId, recordDate);
        ConditionDetailsRecordResponse response =
                ConditionDetailsRecordResponse.fromConditionDetailsRecord(conditionDetailsRecord);
        return ResponseEntity.ok(response);
    }

    /**
     * 몸 상태 기록 삭제 API
     */
    @Operation(summary = "몸 상태 기록 삭제", description = "몸 상태 기록을 삭제하기 위한 API입니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long conditionDetailsRecordId,
            Principal principal) {
        String loginId = principal.getName();
        conditionDetailsRecordService.deleteConditionDetailsRecord(conditionDetailsRecordId, loginId);
        return ResponseEntity.noContent().build();
    }

}
