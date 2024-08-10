package inatools.backend.controller;

import inatools.backend.dto.medication.MedicationRecordRequest;
import inatools.backend.dto.medication.MedicationRecordResponse;
import inatools.backend.service.MedicationRecordService;
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

@Tag(name = "복약 기록 관리", description = "복약 기록을 관리하는 API")
@RestController
@RequestMapping("/api/medication-records")
@RequiredArgsConstructor
public class MedicationRecordController {

    private final MedicationRecordService medicationRecordService;

    @Operation(summary = "복약 기록 생성", description = "복약 기록을 생성합니다.")
    @PostMapping("/{medicationInfoId}")
    public ResponseEntity<MedicationRecordResponse> createMedicationRecord(
            @PathVariable Long medicationInfoId,
            @RequestBody @Valid MedicationRecordRequest request,
            Principal principal) {
        String loginId = principal.getName();
        MedicationRecordResponse response = medicationRecordService.createMedicationRecord(loginId, medicationInfoId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "복약 기록 수정", description = "복약 기록을 수정합니다.")
    @PostMapping("/{recordId}")
    public ResponseEntity<MedicationRecordResponse> updateMedicationRecord(
            @PathVariable Long recordId,
            @RequestBody @Valid MedicationRecordRequest request,
            Principal principal) {
        String loginId = principal.getName();
        MedicationRecordResponse response = medicationRecordService.updateMedicationRecord(loginId, recordId, request);
        return ResponseEntity.ok(response);
    }
}
