package inatools.backend.controller;

import inatools.backend.domain.MedicationInfo;
import inatools.backend.dto.medication.MedicationInfoResponse;
import inatools.backend.dto.medication.MedicationInfoRequest;
import inatools.backend.service.MedicationInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @PostMapping("/{id}")
    public ResponseEntity<MedicationInfoResponse> create(
            @PathVariable("id") Long id,
            @RequestBody @Valid MedicationInfoRequest medicationInfoRequest) {

        MedicationInfo makeMedicationInfo = medicationInfoService.createMedicationInfo(id,
                medicationInfoRequest);
        return ResponseEntity.ok(MedicationInfoResponse.fromMedicationInfo(makeMedicationInfo));
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
