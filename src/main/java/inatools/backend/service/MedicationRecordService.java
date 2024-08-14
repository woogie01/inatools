package inatools.backend.service;

import inatools.backend.domain.MedicationInfo;
import inatools.backend.domain.MedicationRecord;
import inatools.backend.domain.Member;
import inatools.backend.dto.medication.MedicationRecordListResponse;
import inatools.backend.dto.medication.MedicationRecordRequest;
import inatools.backend.dto.medication.MedicationRecordResponse;
import inatools.backend.repository.MedicationInfoRepository;
import inatools.backend.repository.MedicationRecordRepository;
import inatools.backend.repository.MemberRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicationRecordService {

    private final MedicationInfoRepository medicationInfoRepository;
    private final MedicationRecordRepository medicationRecordRepository;
    private final MemberRepository memberRepository;

    private final MemberService memberService;
    private final UserConnectionService userConnectionService;

    /**
     * 복약 기록 생성
     */
    @Transactional
    public MedicationRecordResponse createMedicationRecord(String loginId, MedicationRecordRequest request) {
        MedicationInfo medicationInfo = medicationInfoRepository.findById(request.medicationInfoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 복약 정보가 존재하지 않습니다."));

        Member member = medicationInfo.getMember();
        memberService.checkMember(loginId, member, userConnectionService);

        MedicationRecord record = MedicationRecord.createMedicationRecord(request, medicationInfo);

        return MedicationRecordResponse.fromMedicationRecord(medicationRecordRepository.save(record));
    }

    /**
     * 특정 날짜 복용약 정보와 복약 기록 조회
     */
    public MedicationRecordListResponse getMedicationRecordsList(String loginId,
            Long memberId, LocalDate startDate, LocalDate endDate) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        memberService.checkMember(loginId, member, userConnectionService);

        // 활성화된 복약 정보 목록 가져오기
        List<MedicationInfo> medicationInfos = medicationInfoRepository.findAllByMemberId(memberId);

        // 활성화된 복약 정보의 ID 리스트를 추출
        List<Long> medicationInfoIds = medicationInfos.stream()
                .map(MedicationInfo::getId)
                .toList();

        // 특정 날짜의 복약 기록 가져오기
        List<MedicationRecord> medicationRecords = medicationRecordRepository.findAllByMedicationInfoIdInAndRecordAtBetween(
                medicationInfoIds, startDate, endDate);

        // MedicationRecordResponse로 변환
        List<MedicationRecordResponse> medicationRecordResponses = medicationRecords.stream()
                .map(MedicationRecordResponse::fromMedicationRecord)
                .collect(Collectors.toList());

        // MedicationRecordListResponse로 감싸서 반환
        return new MedicationRecordListResponse(medicationRecordResponses);
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
        memberService.checkMember(loginId, member, userConnectionService);

        return MedicationRecordResponse.fromMedicationRecord(
                medicationRecordRepository.findById(recordId)
                        .map(medicationRecord -> medicationRecord.updateMedicationRecord(request.isTaken()))
                        .orElseThrow(() -> new IllegalArgumentException("해당 복약 기록이 존재하지 않습니다."))
        );
    }

}
