package inatools.backend.auth.jwt.controller;

import inatools.backend.auth.jwt.dto.TokenResponse;
import inatools.backend.auth.jwt.service.TokenReissueService;
import inatools.backend.common.BaseResponse;
import inatools.backend.common.annotation.ExtractPayload;
import inatools.backend.common.annotation.ExtractToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "토큰 관련 로직", description = "토근 발급 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenApiController {

    private final TokenReissueService tokenReissueService;

    @Operation(summary = "Refresh Token 재발급", description = "Refresh Token을 재발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청에 성공했습니다."),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰입니다."),
            @ApiResponse(responseCode = "500", description = "서버와의 연결에 실패했습니다.")
    })
    @PostMapping("/reissue")
    public BaseResponse<TokenResponse> reissueTokens(@ExtractPayload Long memId, @ExtractToken String refreshToken) {
        TokenResponse tokenResponse = tokenReissueService.reissueTokens(memId, refreshToken);
        return new BaseResponse<>(tokenResponse);
    }
}