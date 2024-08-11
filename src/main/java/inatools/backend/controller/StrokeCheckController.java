package inatools.backend.controller;

import inatools.backend.domain.StrokeCheck;
import inatools.backend.dto.strokecheck.StrokeCheckListResponse;
import inatools.backend.dto.strokecheck.StrokeCheckRequest;
import inatools.backend.dto.strokecheck.StrokeCheckResponse;
import inatools.backend.service.StrokeCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "뇌졸중 검사 관련 로직", description = "뇌졸중 검사 API")
@RestController
@RequestMapping("/api/stroke-check")
@RequiredArgsConstructor
public class StrokeCheckController {

    private final StrokeCheckService strokeCheckService;

    /**
     * 뇌졸중 검사 결과 저장 API
     */
    @Operation(summary = "뇌졸중 검사 결과 평균값 업데이트", description = "세 가지 유형의 뇌졸중 검사 결과의 평균 결과값을 저장하기 위한 API입니다.")
    @PostMapping
    public ResponseEntity<StrokeCheckResponse> updateTestAvg(
            @RequestBody @Valid StrokeCheckRequest request, Principal principal) {
        String loginId = principal.getName();
        StrokeCheck strokeCheck = strokeCheckService.createOrUpdateCheckResult(loginId, request);
        StrokeCheckResponse response = StrokeCheckResponse.fromStrokeCheck(strokeCheck);
        return ResponseEntity.ok(response);
    }

    /**
     * 특정 날짜의 뇌졸중 검사 결과 퍼센트 전체 조회 API
     */
    @Operation(summary = "특정 날짜의 모든 유형의 뇌졸중 검사 평균 결과값 조회", description = "특정 날짜의 뇌졸중 검사(얼굴, 발음, 균형) 결과의 모든 평균값을 조회하기 위한 API입니다.")
    @GetMapping("/members/{id}")
    public ResponseEntity<StrokeCheckListResponse> getStrokeCheckResultsByDate(
            @PathVariable("id") Long memberId,
            @RequestParam("date") LocalDate date, Principal principal) {
        String loginId = principal.getName();
        StrokeCheckListResponse response = strokeCheckService.getStrokeCheckResultsByDate(loginId, memberId, date);
        return ResponseEntity.ok(response);
    }

}
