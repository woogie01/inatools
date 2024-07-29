package inatools.backend.auth.security.util;

import inatools.backend.auth.security.dto.UserDetailsDto;
import inatools.backend.common.exception.BaseException;
import inatools.backend.common.exception.GlobalErrorCode;
import inatools.backend.common.exception.error.UserErrorCode;
import inatools.backend.domain.Member;
import inatools.backend.auth.security.domain.CustomUserDetails;
import inatools.backend.auth.jwt.util.JwtTokenProvider;
import inatools.backend.service.MemberFindService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberFindService memberFindService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request에서 Authorization 헤더를 찾음
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtTokenProvider.validateToken(token)) {
                Long id = jwtTokenProvider.getId(token);
                Member member = memberFindService.findById(id);

                if (member != null) {
                    CustomUserDetails customUserDetails = new CustomUserDetails(new UserDetailsDto(member));
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            customUserDetails, "", customUserDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    throw BaseException.type(UserErrorCode.USER_NOT_FOUND);
                }
            } else {
                throw BaseException.type(GlobalErrorCode.INVALID_TOKEN);
            }
        }

        filterChain.doFilter(request, response);
    }
}
