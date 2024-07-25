package inatools.backend.controller;

import inatools.backend.domain.Member;
import inatools.backend.dto.SignUpRequest;
import inatools.backend.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 관련 로직", description = "회원가입 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpRequest> signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        try {
            Member member = memberService.registerMember(signUpRequest);
            return ResponseEntity.ok(SignUpRequest.fromMember(member));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
