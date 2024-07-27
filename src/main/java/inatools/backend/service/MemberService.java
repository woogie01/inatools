package inatools.backend.service;

import inatools.backend.domain.Member;
import inatools.backend.dto.member.SignUpRequest;
import inatools.backend.dto.member.UpdateMemberRequest;
import inatools.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Member registerMember(SignUpRequest signUpRequest) {

        // 중복 회원 검사
        if (memberRepository.existsByUsername(signUpRequest.username())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        // 엔티티 변환
        Member member = Member.createMember(signUpRequest, passwordEncoder);
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateMember(Long id, UpdateMemberRequest updateRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        member.updateMemberInfo(updateRequest);
//        memberRepository.save(member);
        return member;
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
    }

//    @Transactional
//    public Member updateSelfCheck(Long id, SelfCheckRequest selfCheckRequest) {
//        Member member = memberRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
//        member.updateSelfCheck(selfCheckRequest);
//        return member;
//    }

}
