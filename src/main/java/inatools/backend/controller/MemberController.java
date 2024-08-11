package inatools.backend.controller;

import inatools.backend.domain.Member;
import inatools.backend.dto.member.*;
import inatools.backend.service.MedicationInfoService;
import inatools.backend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 관련 로직", description = "회원 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MedicationInfoService medicationInfoService;

    /**
     * 회원 정보 수정 API
     */
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정하기 위한 API입니다.")
    @PutMapping("/{id}")
    public ResponseEntity<UpdateMemberResponse> updateMemberInfo(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest updateRequest, Principal principal) {
        String loginId = principal.getName();
        Member updatedMember = memberService.updateMember(loginId, id, updateRequest);
        UpdateMemberResponse response = UpdateMemberResponse.fromMember(updatedMember);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원 정보 조회 API
     */
    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회하기 위한 API입니다.")
    @GetMapping("/{id}")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(
            @PathVariable("id") Long id, Principal principal) {
        String loginId = principal.getName();
        Member member = memberService.getMember(loginId, id);
        MemberInfoResponse response = MemberInfoResponse.fromMember(member);
        return ResponseEntity.ok(response);
    }

    /**
     * 자가점검 결과(복용약 정보 제외) 저장 API
     */
    @Operation(summary = "자가점검 결과 저장", description = "자가점검 결과(복용약 정보 제외)를 저장하기 위한 API입니다.")
    @PutMapping("/{id}/self-check")
    public ResponseEntity<SelfCheckResponse> registerSelfCheck(@
            PathVariable("id") Long memberId,
            @RequestBody @Valid SelfCheckRequest request, Principal principal) {
        String loginId = principal.getName();
        Member updatedMember = memberService.createSelfCheck(loginId, memberId, request);

        SelfCheckResponse response = SelfCheckResponse.fromMember(updatedMember);
        return ResponseEntity.ok(response);
    }

}
