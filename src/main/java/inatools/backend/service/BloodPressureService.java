package inatools.backend.service;

import inatools.backend.domain.BloodPressure;
import inatools.backend.domain.Member;
import inatools.backend.dto.bloodpressure.BloodPressureRequest;
import inatools.backend.dto.bloodpressure.BloodPressureResponse;
import inatools.backend.repository.BloodPressureRepository;
import inatools.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BloodPressureService {

    private final MemberRepository memberRepository;
    private final BloodPressureRepository bloodPressureRepository;

    @Transactional
    public BloodPressure createBloodPressure(String loginId, Long memberId,
            BloodPressureRequest bloodPressureRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Member.checkMember(loginId, member);

        BloodPressure bloodPressure = BloodPressure.createBloodPressure(bloodPressureRequest, member);
        return bloodPressureRepository.save(bloodPressure);
    }
}
