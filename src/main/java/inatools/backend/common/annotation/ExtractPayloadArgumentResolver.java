package inatools.backend.common.annotation;

import inatools.backend.auth.jwt.util.AuthorizationExtractor;
import inatools.backend.auth.jwt.util.JwtTokenProvider;
import inatools.backend.common.exception.BaseException;
import inatools.backend.common.exception.GlobalErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class ExtractPayloadArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        // 매개변수가 @ExtractPayload 애노테이션을 가지고 있는지 확인
        return parameter.hasParameterAnnotation(ExtractPayload.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        // 1. NativeWebRequest에서 HttpServletRequest를 추출
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // 2. HTTP 요청 헤더에서 JWT 토큰을 추출
        String token = AuthorizationExtractor.extractAccessToken(request)
                .orElseThrow(() -> BaseException.type(GlobalErrorCode.INVALID_PERMISSION));

        if (token.isBlank() || token.isEmpty())
            throw BaseException.type(GlobalErrorCode.INVALID_PERMISSION);


        // 3. 유효한 토큰에서 페이로드(사용자 ID)를 추출하여 반환
        validateTokenIntegrity(token);

        return jwtTokenProvider.getId(token);
    }

    private void validateTokenIntegrity(String token) {
        jwtTokenProvider.validateToken(token);
    }
}