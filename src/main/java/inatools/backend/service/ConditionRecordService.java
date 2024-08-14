package inatools.backend.service;

import inatools.backend.domain.ConditionRecord;
import inatools.backend.domain.Member;
import inatools.backend.dto.condtionrecord.ConditionRecordListResponse;
import inatools.backend.dto.condtionrecord.ConditionRecordRequest;
import inatools.backend.dto.condtionrecord.ConditionRecordResponse;
import inatools.backend.repository.ConditionRecordRepository;
import inatools.backend.repository.MemberRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConditionRecordService {

    private final MemberRepository memberRepository;
    private final ConditionRecordRepository conditionRecordRepository;

    private final MemberService memberService;
    private final UserConnectionService userConnectionService;

    /**
     * 컨디션 기록 저장 또는 수정 로직
     */
    @Transactional
    public ConditionRecord createOrUpdateConditionRecord(String loginId, ConditionRecordRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
        memberService.checkMember(loginId, member, userConnectionService);

        // 해당 날짜에 이미 기록이 있는지 확인
        ConditionRecord existingRecord =
                conditionRecordRepository
                        .findByMemberIdAndRecordAt(member.getId(), request.recordAt())
                        .orElse(null);

        if (existingRecord != null) {
            // 기존 기록이 있으면 수정
            return existingRecord.updateConditionRecord(request);
        } else {
            // 기존 기록이 없으면 새로 생성
            ConditionRecord conditionRecord = ConditionRecord.createConditionRecord(request, member);
            return conditionRecordRepository.save(conditionRecord);
        }
    }

    /**
     * 컨디션 기록 조회 로직
     */
    public ConditionRecordListResponse getConditionRecord(String loginId, Long memberId, LocalDate startDate, LocalDate endDate) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        memberService.checkMember(loginId, member, userConnectionService);

        List<ConditionRecord> conditionRecordList =
                conditionRecordRepository.findAllByMemberIdAndRecordAtBetween(memberId, startDate, endDate);
        List<ConditionRecordResponse> conditionRecordResponseList = conditionRecordList.stream()
                .map(ConditionRecordResponse::fromConditionRecord)
                .toList();

        return new ConditionRecordListResponse(conditionRecordResponseList);
    }

}
