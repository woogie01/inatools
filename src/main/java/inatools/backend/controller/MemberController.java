package inatools.backend.controller;

import inatools.backend.auth.jwt.persistence.TokenPersistenceAdapter;
import inatools.backend.auth.jwt.util.JwtTokenProvider;
import inatools.backend.common.BaseResponse;
import inatools.backend.domain.Member;
import inatools.backend.dto.member.*;
import inatools.backend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 회원 정보 수정 API
     */
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정하기 위한 API입니다.")
    @PutMapping("/{id}")
    public ResponseEntity<UpdateMemberResponse> updateMemberInfo(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest updateRequest) {
        Member updatedMember = memberService.updateMember(id, updateRequest);
        UpdateMemberResponse response = UpdateMemberResponse.fromMember(updatedMember);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원 정보 조회 API
     */
    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회하기 위한 API입니다.")
    @GetMapping("/{id}")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(
            @PathVariable("id") Long id) {
        Member member = memberService.getMember(id);
        MemberInfoResponse response = MemberInfoResponse.fromMember(member);
        return ResponseEntity.ok(response);
    }

    /**
     * 자가점검 정보 수정 API
     */
//    @Operation(summary = "자가점검 정보 수정", description = "자가점검 정보를 수정하기 위한 API입니다.")
//    @PutMapping("/{id}/self-check")
//    public ResponseEntity<SelfCheckResponse> updateSelfCheckInfo(
//            @PathVariable Long id,
//            @RequestBody SelfCheckRequest selfCheckRequest) {
//
//        Member updatedSelfCheck = memberService.updateSelfCheck(id, selfCheckRequest);
//        SelfCheckResponse response = SelfCheckResponse.fromMember(updatedSelfCheck);
//        return ResponseEntity.ok(response);
//    }

}
