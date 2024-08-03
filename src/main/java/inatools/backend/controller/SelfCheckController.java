package inatools.backend.controller;

import inatools.backend.domain.Member;
import inatools.backend.dto.member.SelfCheckRequest;
import inatools.backend.dto.member.SelfCheckResponse;
import inatools.backend.service.SelfCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "자가점검 관련 로직", description = "자가점검 API")
@RestController
@RequestMapping("/api/self-check")
@RequiredArgsConstructor
public class SelfCheckController {

    private final SelfCheckService selfCheckService;

    /**
     * 자가점검 결과 등록 API
     */
    @Operation(summary = "자가점검 등록", description = "자가점검을 등록하기 위한 API입니다.")
    @PutMapping("/{memberId}")
    public ResponseEntity<SelfCheckResponse> registerSelfCheck(
            @PathVariable("memberId") Long memberId,
            @RequestBody @Valid SelfCheckRequest request) {

        Member updatedMember = selfCheckService.registerSelfCheck(memberId, request);
    }

}
