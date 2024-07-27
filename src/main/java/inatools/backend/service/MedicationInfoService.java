package inatools.backend.service;

import inatools.backend.domain.MedicationInfo;
import inatools.backend.dto.medication.MedicationInfoRequest;
import inatools.backend.repository.MedicationInfoRepository;
import inatools.backend.repository.MemberRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public MedicationInfo createMedicationInfo(Long id, MedicationInfoRequest medicationInfoRequest) {
        if (memberRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
        }
        MedicationInfo medicationInfo = MedicationInfo.createMedicationInfo(medicationInfoRequest);
        return medicationInfoRepository.save(medicationInfo);
    }
}
