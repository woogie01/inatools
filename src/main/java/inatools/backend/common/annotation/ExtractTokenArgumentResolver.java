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
public class ExtractTokenArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     *  스프링 MVC의 HandlerMethodArgumentResolver를 구현하여 매개변수 주입 로직을 정의
     */

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        // 매개변수가 @ExtractToken 애노테이션을 가지고 있는지 확인
        return parameter.hasParameterAnnotation(ExtractToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        // 1. NativeWebRequest에서 HttpServletRequest를 추출
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // 2. HTTP 요청 헤더에서 Refresh-Token을 추출
        String token = AuthorizationExtractor.extractRefreshToken(request)
                .orElseThrow(() -> BaseException.type(GlobalErrorCode.INVALID_PERMISSION));

        // 3. 추출된 토큰의 유효성을 검증하고 반환
        validateTokenIntegrity(token);

        return token;
    }

    private void validateTokenIntegrity(String token) {
        jwtTokenProvider.validateToken(token);
    }
}