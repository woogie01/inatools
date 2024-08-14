package inatools.backend.service;

import inatools.backend.domain.Member;
import inatools.backend.dto.member.SelfCheckRequest;
import inatools.backend.dto.member.UpdateMemberRequest;
import inatools.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserConnectionService userConnectionService;

    @Transactional
    public Member updateMember(String loginId, Long id, UpdateMemberRequest updateRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        checkMember(loginId, member, userConnectionService);
        member.updateMemberInfo(updateRequest, passwordEncoder);
//        memberRepository.save(member);
        return member;
    }

    public Member getMember(String loginId, Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        checkMember(loginId, member, userConnectionService);
        return member;
    }

    /**
     * 자가점검 결과 저장
     */
    @Transactional
    public Member createSelfCheck(String loginId, Long memberId, SelfCheckRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        checkMember(loginId, member, userConnectionService);
        member.updateBasicSelfCheck(request);
        return member;
    }

    /**
     * 접근 및 수정 권한 체크
     */
    public void checkMember(String loginId, Member targetMember, UserConnectionService userConnectionService) {

        Member loginMember = memberRepository.findByUserId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("로그인 회원이 존재하지 않습니다."));

        // 로그인 회원과 대상 회원이 일치하지 않고, 수정 권한도 없는 경우
        if (!loginId.equals(targetMember.getUserId()) &&
                !userConnectionService.hasPermissionToModify(loginMember, targetMember)) {
            throw new IllegalArgumentException("해당 데이터에 대한 접근 및 수정 권한이 없습니다..");
        }
    }
}

