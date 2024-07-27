package inatools.backend.controller;

import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.Member;
import inatools.backend.dto.medication.MedicationInfoListResponse;
import inatools.backend.dto.medication.MedicationInfoResponse;
import inatools.backend.dto.medication.MedicationInfoRequest;
import inatools.backend.dto.member.MemberInfoResponse;
import inatools.backend.service.MedicationInfoService;
import inatools.backend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
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
    @Operation(summary = "복용약 정보 추가", description = "복용약 정보를 생성하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<MedicationInfoResponse> create(
            @RequestBody @Valid MedicationInfoRequest medicationInfoRequest) {
        MedicationInfo makeMedicationInfo = medicationInfoService.createMedicationInfo(
                medicationInfoRequest.memberId(), medicationInfoRequest);
        return ResponseEntity.ok(MedicationInfoResponse.fromMedicationInfo(makeMedicationInfo));
    }

    /**
     * 복용약 정보 리스트 조회 API
     */
    @Operation(summary = "복용약 정보 리스트 조회", description = "복용약 정보 리스트를 조회하기 위한 API입니다.")
    @GetMapping("/members/{memberId}")
    public ResponseEntity<MedicationInfoListResponse> getMedicationInfoList(
            @PathVariable Long memberId) {
        MedicationInfoListResponse response = medicationInfoService.getMedicationInfoListByMemberId(
                memberId);
        return ResponseEntity.ok(response);
    }

    /**
     * 복용약 정보 삭제 API
     */
    @Operation(summary = "복용약 정보 삭제", description = "복용약 정보를 삭제하기 위한 API입니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        medicationInfoService.deleteMedicationInfo(id);
        return ResponseEntity.noContent().build();
    }

}
