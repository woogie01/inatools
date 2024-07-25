package inatools.backend.service;

import inatools.backend.domain.Member;
import inatools.backend.dto.SignUpRequest;
import inatools.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member registerMember(SignUpRequest signUpRequest) {
        if (memberRepository.findByName(signUpRequest.name()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        Member member = Member.createMember(signUpRequest);
        return memberRepository.save(member);
    }
}
