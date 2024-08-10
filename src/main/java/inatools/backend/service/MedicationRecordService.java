package inatools.backend.service;

import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.MedicationRecord;
import inatools.backend.domain.Member;
import inatools.backend.dto.medication.MedicationRecordRequest;
import inatools.backend.dto.medication.MedicationRecordResponse;
import inatools.backend.repository.MedicationInfoRepository;
import inatools.backend.repository.MedicationRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicationRecordService {

    private final MedicationInfoRepository medicationInfoRepository;
    private final MedicationRecordRepository medicationRecordRepository;

    /**
     * 복약 기록 생성
     */
    @Transactional
    public MedicationRecordResponse createMedicationRecord(String loginId,
            Long medicationInfoId, MedicationRecordRequest request) {
        MedicationInfo medicationInfo = medicationInfoRepository.findById(medicationInfoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 복약 정보가 존재하지 않습니다."));

        Member member = medicationInfo.getMember();
        Member.checkMember(loginId, member);

        MedicationRecord record = MedicationRecord.createMedicationRecord(request, medicationInfo);

        return MedicationRecordResponse.fromMedicationRecord(medicationRecordRepository.save(record));
    }

    /**
     * 복약 기록 여부 체크
     */
    @Transactional
    public MedicationRecordResponse updateMedicationRecord(String loginId,
            Long recordId, MedicationRecordRequest request) {
        MedicationInfo medicationInfo = medicationInfoRepository.findById(request.medicationInfoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 복약 정보가 존재하지 않습니다."));
        Member member = medicationInfo.getMember();
        Member.checkMember(loginId, member);

        return MedicationRecordResponse.fromMedicationRecord(
                medicationRecordRepository.findById(recordId)
                        .map(medicationRecord -> medicationRecord.updateMedicationRecord(request.isTaken()))
                        .orElseThrow(() -> new IllegalArgumentException("해당 복약 기록이 존재하지 않습니다."))
        );
    }
}
