package inatools.backend.service;

import com.sun.jdi.request.DuplicateRequestException;
import inatools.backend.domain.ConditionRecord;
import inatools.backend.domain.Member;
import inatools.backend.dto.condtionrecord.ConditionRecordRequest;
import inatools.backend.dto.condtionrecord.ConditionRecordResponse;
import inatools.backend.repository.ConditionRecordRepository;
import inatools.backend.repository.MemberRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConditionRecordService {

    private final MemberRepository memberRepository;
    private final ConditionRecordRepository conditionRecordRepository;

    /**
     * 컨디션 기록 저장 로직
     */
    @Transactional
    public ConditionRecord createConditionRecord(String loginId, ConditionRecordRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Member.checkMember(loginId, member);

        ConditionRecord conditionRecord = ConditionRecord.createConditionRecord(request, member);
        return conditionRecordRepository.save(conditionRecord);
    }

    /**
     * 컨디션 기록 조회 로직
     */
    public ConditionRecordResponse getConditionRecord(String loginId, Long memberId, LocalDate recordDate) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Member.checkMember(loginId, member);

        return conditionRecordRepository.findByMemberIdAndRecordDate(memberId, recordDate)
                .map(ConditionRecordResponse::fromConditionRecord)
                .orElseThrow(() -> new IllegalArgumentException("해당 컨디션 기록이 존재하지 않습니다."));
    }

    /**
     * 컨디션 기록 수정 로직
     */
    @Transactional
    public ConditionRecord updateConditionRecord(Long conditionRecordId, String loginId,
            ConditionRecordRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Member.checkMember(loginId, member);

        return conditionRecordRepository.findById(conditionRecordId)
                .map(conditionRecord -> conditionRecord.updateConditionRecord(request))
                .orElseThrow(() -> new IllegalArgumentException("해당 컨디션 기록이 존재하지 않습니다."));
    }
}
