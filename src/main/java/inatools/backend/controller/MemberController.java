package inatools.backend.controller;

import inatools.backend.domain.Member;
import inatools.backend.dto.SignUpRequest;
import inatools.backend.dto.SignUpResponse;
import inatools.backend.dto.UpdateMemberRequest;
import inatools.backend.dto.UpdateMemberResponse;
import inatools.backend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * 회원가입 API
     */
    @Operation(summary = "회원가입", description = "회원가입을 위한 API입니다.")
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignUpRequest signUpRequest) {

        try {
            Member member = memberService.registerMember(signUpRequest);
            SignUpResponse response = SignUpResponse.fromMember(member);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * 회원 수정 API
     */
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정하기 위한 API입니다.")
    @PutMapping("/{id}")
    public ResponseEntity<UpdateMemberResponse> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest updateRequest) {
        if (!id.equals(updateRequest.id())) {
            return ResponseEntity.badRequest().build();
        }
        Member updatedMember = memberService.updateMember(updateRequest);
        UpdateMemberResponse response = UpdateMemberResponse.fromMember(updatedMember);
        return ResponseEntity.ok(response);
    }

    /**
     * 비밀번호 수정 API
     */
//    @PutMapping("/{id}/password")
//    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody PasswordUpdateRequest passwordUpdateDTO) {
//        memberService.updatePassword(id, passwordUpdateDTO);
//        return ResponseEntity.noContent().build();
//    }

}
