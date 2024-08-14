package inatools.backend.service;

import inatools.backend.domain.Member;
import inatools.backend.domain.StrokeCheck;
import inatools.backend.dto.strokecheck.StrokeCheckListResponse;
import inatools.backend.dto.strokecheck.StrokeCheckRequest;
import inatools.backend.dto.strokecheck.StrokeCheckResponse;
import inatools.backend.repository.MemberRepository;
import inatools.backend.repository.StrokeCheckRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StrokeCheckService {

    private final MemberRepository memberRepository;
    private final StrokeCheckRepository strokeCheckRepository;

    private final MemberService memberService;
    private final UserConnectionService userConnectionService;

    /**
     * 각 테스트 결과 저장 로직
     */
    @Transactional
    public StrokeCheck createOrUpdateCheckResult(String loginId, StrokeCheckRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        memberService.checkMember(loginId, member, userConnectionService);

        // 해당 테스트 타입과 기록 날짜로 기존 데이터 조회
        StrokeCheck existingStrokeCheck = strokeCheckRepository.findByMemberAndTestTypeAndRecordAtBetween(
                member, request.testType(), request.recordAt(), request.recordAt());

        if (existingStrokeCheck != null) {
            // 기존 데이터가 있다면, 평균 값을 갱신
            existingStrokeCheck.updateTestResult(request.testResultPercent());
            return strokeCheckRepository.save(existingStrokeCheck);
        } else {
            // 기존 데이터가 없다면 새로 생성
            StrokeCheck strokeCheck = StrokeCheck.createStrokeCheck(request, member);
            return strokeCheckRepository.save(strokeCheck);
        }
    }

    public StrokeCheckListResponse getStrokeCheckResultsByDate(String loginId, Long memberId, LocalDate date) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        memberService.checkMember(loginId, member, userConnectionService);

        List<StrokeCheck> strokeChecks = strokeCheckRepository.findAllByRecordAt(date);
        List<StrokeCheckResponse> strokeCheckResponseList = strokeChecks.stream()
                .map(StrokeCheckResponse::fromStrokeCheck)
                .collect(Collectors.toList());
        return new StrokeCheckListResponse(strokeCheckResponseList);
    }
}
