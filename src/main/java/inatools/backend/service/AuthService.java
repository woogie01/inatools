package inatools.backend.service;

import inatools.backend.auth.jwt.persistence.TokenPersistenceAdapter;
import inatools.backend.auth.jwt.util.JwtTokenProvider;
import inatools.backend.common.exception.BaseException;
import inatools.backend.common.exception.error.UserErrorCode;
import inatools.backend.domain.Member;
import inatools.backend.domain.Password;
import inatools.backend.dto.member.LoginResponse;
import inatools.backend.dto.member.SignUpRequest;
import inatools.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberFindService memberFindService;
    private final MemberRepository memberRepository;
    private final TokenPersistenceAdapter tokenPersistenceAdapter;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(SignUpRequest signUpRequest) {

        // 중복 회원 검사
        if (memberRepository.existsByUserId(signUpRequest.userId())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        // 엔티티 변환
        Member member = Member.createMember(signUpRequest, passwordEncoder);
        return memberRepository.save(member);
    }

    @Transactional
    public LoginResponse login(String userId, String password) {

        Member member = memberFindService.findByUserId(userId);

        // 비밀번호 검증
        Password memberPassword = member.getPassword();
        comparePassword(password, memberPassword);

        // 토근 발급
        String accessToken = jwtTokenProvider.generateAccessToken(member.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(member.getId());
        tokenPersistenceAdapter.synchronizeRefreshToken(member.getId(), refreshToken);

        return new LoginResponse(
                member.getId(),
                member.getUsername(),
                member.getUserId(),
                accessToken,
                refreshToken
        );
    }

    private void comparePassword(String password, Password memPassword) {
        if(!memPassword.isSamePassword(password, passwordEncoder)) {
            throw BaseException.type(UserErrorCode.PASSWORD_MISMATCH);
        }
    }
}
