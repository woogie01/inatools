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

    @Transactional
    public Member updateMember(String loginId, Long id, UpdateMemberRequest updateRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Member.checkMember(loginId, member);
        member.updateMemberInfo(updateRequest, passwordEncoder);
//        memberRepository.save(member);
        return member;
    }

    public Member getMember(String loginId, Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Member.checkMember(loginId, member);
        return member;
    }

    /**
     * 자가점검 결과 저장
     */
    @Transactional
    public Member createSelfCheck(String loginId, Long memberId, SelfCheckRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Member.checkMember(loginId, member);
        member.updateBasicSelfCheck(request);
        return member;
    }
}

