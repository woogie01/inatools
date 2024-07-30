package inatools.backend.auth.jwt.persistence;

import inatools.backend.auth.jwt.domain.Token;
import inatools.backend.auth.jwt.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Repository
@RequiredArgsConstructor
public class RdbTokenPersistenceAdapter implements TokenPersistenceAdapter {

    private final TokenRepository tokenRepository;

    @Transactional
    @Override
    public void synchronizeRefreshToken(Long memberId, String refreshToken) {
        tokenRepository.findByMemId(memberId)
                .ifPresentOrElse(
                        token -> token.updateRefreshToken(refreshToken),
                        () -> tokenRepository.save(Token.generateToken(memberId, refreshToken))
                );
    }

    @Transactional
    @Override
    public void manageRefreshTokenRotation(Long memId, String refreshToken) {
        tokenRepository.manageRefreshTokenRotation(memId, refreshToken);
    }

    @Transactional
    @Override
    public void deleteRefreshTokenByMemId(Long memId) {
        tokenRepository.deleteByMemId(memId);
    }

    @Override
    public boolean isExistsRefreshToken(Long memId, String refreshToken) {
        return tokenRepository.existsByMemIdAndRefreshToken(memId, refreshToken);
    }
}