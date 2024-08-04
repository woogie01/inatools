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
    public Member updateMember(Long id, UpdateMemberRequest updateRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        member.updateMemberInfo(updateRequest, passwordEncoder);
//        memberRepository.save(member);
        return member;
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
    }

    /**
     * 자가점검 결과 저장
     */
    @Transactional
    public Member createSelfCheck(Long memberId, SelfCheckRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updateBasicSelfCheck(request);
        return member;
    }
}
