package inatools.backend.service;

import inatools.backend.domain.Member;
import inatools.backend.dto.SignUpRequest;
import inatools.backend.dto.UpdateMemberRequest;
import inatools.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Member registerMember(SignUpRequest signUpRequest) {

        // 중복 회원 검사
        if (memberRepository.existsByUsername(signUpRequest.username())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        // 엔티티 변환
        Member member = Member.createMember(signUpRequest, bCryptPasswordEncoder);
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateMember(UpdateMemberRequest updateRequest) {
        Member member = memberRepository.findById(updateRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        member.updateMemberInfo(updateRequest);
//        memberRepository.save(member);
        return member;
    }

}
