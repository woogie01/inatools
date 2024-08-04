package inatools.backend.controller;

import inatools.backend.domain.BloodPressure;
import inatools.backend.dto.bloodpressure.BloodPressureListResponse;
import inatools.backend.dto.bloodpressure.BloodPressureRequest;
import inatools.backend.dto.bloodpressure.BloodPressureResponse;
import inatools.backend.service.BloodPressureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "혈압 측정 기록 관련 로직", description = "혈압 API")
@RestController
@RequestMapping("/api/blood-pressure")
@RequiredArgsConstructor
public class BloodPressureController {

    private final BloodPressureService bloodPressureService;

    /**
     * 혈압 측정 기록 생성 API
     */
    @Operation(summary = "혈압 측정 기록 추가", description = "혈압 측정 기록을 추가하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<BloodPressureResponse> create(
            @RequestBody @Valid BloodPressureRequest bloodPressureRequest, Principal principal) {
        String loginId = principal.getName();
        BloodPressure bloodPressure = bloodPressureService.createBloodPressure(loginId,
                bloodPressureRequest.memberId(), bloodPressureRequest);
        BloodPressureResponse response = BloodPressureResponse.fromBloodPressure(bloodPressure);
        return ResponseEntity.ok(response);
    }

    /**
     * 혈압 측정 기록 리스트 조회 API
     */
    @Operation(summary = "혈압 측정 기록 리스트 조회", description = "특정 날짜의 혈압 측정 기록들를 조회하기 위한 API입니다.")
    @GetMapping("/members/{memberId}")
    public ResponseEntity<BloodPressureListResponse> getBloodPressureList(
            @PathVariable Long memberId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        BloodPressureListResponse response =
                bloodPressureService.getBloodPressureListByMemberIdAndDate(memberId, date);
        return ResponseEntity.ok(response);
    }

//
//     /**
//      * 혈압 측정 기록 삭제 API
//      */
//     @Operation(summary = "혈압 측정 기록 삭제", description = "혈압 측정 기록을 삭제하기 위한 API입니다.")
//     @DeleteMapping("/{bloodPressureId}")
//     public ResponseEntity<BaseResponse> deleteBloodPressure(@PathVariable Long bloodPressureId) {
//         bloodPressureService.deleteBloodPressure(bloodPressureId);
//         return ResponseEntity.ok(BaseResponse.success());
//     }
//
//     /**
//      * 혈압 측정 기록 수정 API
//      */
//     @Operation(summary = "혈압 측정 기록 수정", description = "혈압 측정 기록을 수정하기 위한 API입니다.")
//     @PutMapping("/{bloodPressureId}")
//     public ResponseEntity<BloodPressureResponse> updateBloodPressure(@PathVariable Long bloodPressure
}
