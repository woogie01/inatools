package inatools.backend.service;

import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.Member;
import inatools.backend.dto.medication.MedicationInfoListResponse;
import inatools.backend.dto.medication.MedicationInfoRequest;
import inatools.backend.dto.medication.MedicationInfoResponse;
import inatools.backend.repository.MedicationInfoRepository;
import inatools.backend.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicationInfoService {

    private final MemberRepository memberRepository;
    private final MedicationInfoRepository medicationInfoRepository;

    @Transactional
    public MedicationInfo createMedicationInfo(Long memberId,
            MedicationInfoRequest medicationInfoRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        MedicationInfo medicationInfo = MedicationInfo.createMedicationInfo(medicationInfoRequest, member);
        return medicationInfoRepository.save(medicationInfo);
    }

    public MedicationInfoListResponse getMedicationInfoListByMemberId(Long memberId) {
        List<MedicationInfo> medicationInfoList = medicationInfoRepository.findAllByMemberId(memberId);
        List<MedicationInfoResponse> medicationInfoResponseList = medicationInfoList.stream()
                .map(MedicationInfoResponse::fromMedicationInfo)
                .collect(Collectors.toList());

        return new MedicationInfoListResponse(medicationInfoResponseList);
    }


    @Transactional
    public void deleteMedicationInfo(Long id) {
        // TODO: 인증 인가 로직 추가되면 해당 회원이 본인이 맞는지 확인하는 로직 추가
        medicationInfoRepository.deleteById(id);
    }
}
