package inatools.backend.auth.jwt.service;

import inatools.backend.auth.jwt.dto.TokenResponse;
import inatools.backend.auth.jwt.persistence.TokenPersistenceAdapter;
import inatools.backend.auth.jwt.util.JwtTokenProvider;
import inatools.backend.common.exception.BaseException;
import inatools.backend.common.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenReissueService {

    private final TokenPersistenceAdapter tokenPersistenceAdapter;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Refresh Token을 사용하여 새로운 Access Token과 Refresh Token을 발급
     *
     */
    @Transactional
    public TokenResponse reissueTokens(Long memId, String refreshToken) {

        if (!tokenPersistenceAdapter.isExistsRefreshToken(memId, refreshToken)) {
            throw BaseException.type(GlobalErrorCode.INVALID_TOKEN);
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(memId);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(memId);

        tokenPersistenceAdapter.manageRefreshTokenRotation(memId, newRefreshToken);

        return new TokenResponse(newAccessToken, newRefreshToken);
    }
}
