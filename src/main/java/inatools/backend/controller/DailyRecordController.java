package inatools.backend.controller;

import inatools.backend.dto.dailyrecord.DailyRecordResponse;
import inatools.backend.service.BloodPressureService;
import inatools.backend.service.ConditionDetailsRecordService;
import inatools.backend.service.ConditionRecordService;
import inatools.backend.service.MedicationInfoService;
import inatools.backend.service.MedicationRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Daily Record", description = "특정 날짜의 모든 기록 조회 API")
@RestController
@RequestMapping("/api/daily-record")
@RequiredArgsConstructor
public class DailyRecordController {

    private final BloodPressureService bloodPressureService;
    private final MedicationRecordService medicationRecordService;
    private final ConditionRecordService conditionRecordService;
    private final ConditionDetailsRecordService conditionDetailsRecordService;

    @Operation(summary = "특정 날짜의 모든 기록 조회", description = "특정 날짜의 모든 기록을 조회하는 API입니다.")
    @GetMapping
    public ResponseEntity<DailyRecordResponse> getDailyRecords(
            @RequestParam Long memberId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            Principal principal) {

        String loginId = principal.getName();

        // 각각의 서비스에서 데이터 조회
        var bloodPressureRecords = bloodPressureService.getBloodPressureListByMemberIdAndDate(loginId, memberId, startDate, endDate);
        var medicationRecords = medicationRecordService.getMedicationRecordsList(loginId, memberId, startDate, endDate);
        var conditionRecord = conditionRecordService.getConditionRecord(loginId, memberId, startDate, endDate);
        var conditionDetailsRecords = conditionDetailsRecordService.getConditionDetailsRecordList(loginId, memberId, startDate, endDate);

        // DailyRecordResponse DTO에 모든 데이터를 담아 반환
        DailyRecordResponse response = new DailyRecordResponse(bloodPressureRecords, medicationRecords, conditionRecord, conditionDetailsRecords);
        return ResponseEntity.ok(response);
    }
}
