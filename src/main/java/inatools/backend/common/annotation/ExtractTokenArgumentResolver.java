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

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ExtractToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String token = AuthorizationExtractor.extractToken(request)
                .orElseThrow(() -> BaseException.type(GlobalErrorCode.INVALID_PERMISSION));

        validateTokenIntegrity(token);

        return token;
    }

    private void validateTokenIntegrity(String token) {
        jwtTokenProvider.validateToken(token);
    }
}