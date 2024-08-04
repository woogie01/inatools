package inatools.backend.service;

import inatools.backend.domain.BloodPressure;
import inatools.backend.domain.Member;
import inatools.backend.dto.bloodpressure.BloodPressureListResponse;
import inatools.backend.dto.bloodpressure.BloodPressureRequest;
import inatools.backend.dto.bloodpressure.BloodPressureResponse;
import inatools.backend.repository.BloodPressureRepository;
import inatools.backend.repository.MemberRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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

    public BloodPressureListResponse getBloodPressureListByMemberIdAndDate(Long memberId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<BloodPressure> bloodPressureList = bloodPressureRepository.findAllByMemberId(memberId, startOfDay, endOfDay);
        List<BloodPressureResponse> bloodPressureResponseList = bloodPressureList.stream()
                .map(BloodPressureResponse::fromBloodPressure)
                .toList();

        return new BloodPressureListResponse(bloodPressureResponseList);
    }

    public void deleteBloodPressure(Long bloodPressureId, String loginId) {
        Member member = memberRepository.findByUserId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Member.checkMember(loginId, member);
        BloodPressure bloodPressure = bloodPressureRepository.findById(bloodPressureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 혈압 측정 기록이 존재하지 않습니다."));
        bloodPressureRepository.delete(bloodPressure);
    }
}
