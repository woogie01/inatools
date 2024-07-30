package inatools.backend.auth.jwt.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationExtractor {

    private static final String BEARER_TYPE = "Bearer";

    /**
     * HTTP 요청의 Authorization 헤더에서 JWT 토큰을 추출
     *
     */
    public static Optional<String> extractToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);
        if (isAuthorizationHeaderEmpty(header)) {
            return Optional.empty();
        }
        return checkToken(header.split(" "));
    }

    private static boolean isAuthorizationHeaderEmpty(String header) {
        return !StringUtils.hasText(header);
    }

    private static Optional<String> checkToken(String[] value) {
        if (value.length == 2 && value[0].equals(BEARER_TYPE)) {
            return Optional.ofNullable(value[1]);
        }
        return Optional.empty();
    }
}
