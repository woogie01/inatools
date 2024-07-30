package inatools.backend.config;


import inatools.backend.auth.jwt.util.JwtTokenProvider;
import inatools.backend.common.annotation.ExtractPayloadArgumentResolver;
import inatools.backend.common.annotation.ExtractTokenArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * JWT 토큰 추출 및 페이로드 접근 허용
     * 1. 컨트롤러 메서드에서 직접 JWT 토큰이나 그 페이로드를 추출하고 사용
     * 2. 토큰과 페이로드를 메서드 매개변수로 받도록 허용
     *
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ExtractTokenArgumentResolver(jwtTokenProvider));
        resolvers.add(new ExtractPayloadArgumentResolver(jwtTokenProvider));
    }
}