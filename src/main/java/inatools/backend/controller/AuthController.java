package inatools.backend.controller;

import inatools.backend.auth.jwt.persistence.TokenPersistenceAdapter;
import inatools.backend.auth.jwt.util.AuthorizationExtractor;
import inatools.backend.auth.jwt.util.JwtTokenProvider;
import inatools.backend.common.BaseResponse;
import inatools.backend.domain.Member;
import inatools.backend.dto.member.*;
import inatools.backend.service.AuthService;
import inatools.backend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 관련 로직", description = "인증 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenPersistenceAdapter tokenPersistenceAdapter;

    /**
     * 회원가입 API
     */
    @Operation(summary = "회원가입", description = "회원가입을 위한 API입니다.")
    @PostMapping
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignUpRequest signUpRequest) {

        try {
            Member member = authService.signUp(signUpRequest);

            // 토큰 발급
            String accessToken = jwtTokenProvider.generateAccessToken(member.getId());
            String refreshToken = jwtTokenProvider.generateRefreshToken(member.getId());
            tokenPersistenceAdapter.synchronizeRefreshToken(member.getId(), refreshToken);

            SignUpResponse response = SignUpResponse.fromMember(member, accessToken, refreshToken);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * 아이디 중복 확인 API
     */
    @Operation(summary = "아이디 중복 확인", description = "회원가입 시 아이디 중복을 확인하기 위한 API입니다.")
    @GetMapping("/check-username")
    public ResponseEntity<BaseResponse> checkUsername(@RequestParam String username) {
        boolean isUsernameTaken = authService.isUsernameTaken(username);
        if (isUsernameTaken) {
            return ResponseEntity.badRequest().body(new BaseResponse("해당 아이디는 이미 사용중입니다."));
        }
        return ResponseEntity.ok(new BaseResponse("사용 가능한 아이디입니다."));
    }

    /**
     * 로그인 API
     */
    @Operation(summary = "로그인", description = "회원이 로그인을 진행합니다.")
    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return new BaseResponse<>(authService.login(request.userId(), request.password()));
    }

    /**
     * 로그아웃 API
     */
    @Operation(summary = "로그아웃", description = "회원이 로그아웃을 진행합니다.")
    @PostMapping("/logout")
    public ResponseEntity<BaseResponse> logout(HttpServletRequest request) {

        String refreshToken = AuthorizationExtractor.extractRefreshToken(request).orElseThrow(() -> new IllegalArgumentException("Refresh Token is missing"));
        authService.logout(refreshToken);

        return ResponseEntity.ok(new BaseResponse(200, "Success", "로그아웃 성공"));
    }
}
