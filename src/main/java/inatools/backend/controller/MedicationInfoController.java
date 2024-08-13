package inatools.backend.controller;

import inatools.backend.dto.medication.MedicationInfoListResponse;
import inatools.backend.dto.medication.MedicationInfoRequest;
import inatools.backend.service.MedicationInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "복용약 리스트 관련 로직", description = "회원 API")
@RestController
@RequestMapping("/api/medication-info")
@RequiredArgsConstructor
public class MedicationInfoController {

    private final MedicationInfoService medicationInfoService;

    /**
     * 복용약 정보 생성 API
     */
    @Operation(summary = "복용약 정보(단일, 복수) 추가", description = "복용약 정보를 추가하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<MedicationInfoListResponse> create(
            @RequestBody @Valid MedicationInfoRequest medicationInfoRequest, Principal principal) {
        String loginId = principal.getName();
        MedicationInfoListResponse response =
                medicationInfoService.createMedicationInfoList(loginId, medicationInfoRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * 복용약 정보 리스트 조회 API
     */
    @Operation(summary = "복용약 정보 리스트 조회", description = "활성화된 복용약 정보들을 조회하기 위한 API입니다.")
    @GetMapping("/members/{id}")
    public ResponseEntity<MedicationInfoListResponse> getMedicationInfoList(
            @PathVariable("id") Long memberId, Principal principal) {
        String loginId = principal.getName();
        MedicationInfoListResponse response = medicationInfoService.getMedicationInfoListByMemberId(loginId, memberId);
        return ResponseEntity.ok(response);
    }

    /**
     * 복용약 정보 삭제 API
     */
    @Operation(summary = "복용약 정보 삭제", description = "복용약 정보를 비활성화하기 위한 API입니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id, Principal principal) {
        String loginId = principal.getName();
        medicationInfoService.deleteMedicationInfo(id, loginId);
        return ResponseEntity.noContent().build();
    }

}
