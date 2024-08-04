package inatools.backend.auth.jwt.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationExtractor {

    private static final String BEARER_TYPE = "Bearer";

    /**
     * HTTP 요청의 Authorization 헤더에서 Access 토큰을 추출
     *
     */
    public static Optional<String> extractAccessToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);
        if (isAuthorizationHeaderEmpty(header)) {
            return Optional.empty();
        }
        return checkToken(header.split(" "));
    }

    /**
     * HTTP 요청의 Refresh-Token 헤더에서 Access 토큰을 추출
     *
     */
    public static Optional<String> extractRefreshToken(HttpServletRequest request) {
        String header = request.getHeader("Refresh-Token");
        if (isAuthorizationHeaderEmpty(header)) {
            return Optional.empty();
        }
        return Optional.of(header);
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
