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

    public BloodPressureResponse createBloodPressure(String loginId, Long aLong,
            BloodPressureRequest bloodPressureRequest) {
        Member member = memberRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        checkMember(loginId, member);

        BloodPressure bloodPressure = BloodPressure.createBloodPressure(bloodPressureRequest, member);
        return BloodPressureResponse.of(bloodPressureRepository.save(bloodPressure));
    }

    /*
     * 로그인한 회원인지 체크
     */
    private static void checkMember(String loginId, Member member) {
        if (!loginId.equals(member.getUserId())) {
            throw new IllegalArgumentException("요청 회원과 일치하지 않습니다.");
        }
    }
}
