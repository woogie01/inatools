package inatools.backend.service;

import inatools.backend.domain.Member;
import inatools.backend.dto.member.SelfCheckRequest;
import inatools.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SelfCheckService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member registerSelfCheck(Long memberId, SelfCheckRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updateSelfCheck(request);
        return member;
    }
}
