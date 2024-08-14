package inatools.backend.service;

import inatools.backend.domain.UserCareConnection;
import inatools.backend.domain.Member;
import inatools.backend.dto.userconnection.UserCareConnectionListResponse;
import inatools.backend.dto.userconnection.UserCareConnectionRequest;
import inatools.backend.dto.userconnection.UserCareConnectionResponse;
import inatools.backend.dto.userconnection.UserConnectionReplyRequest;
import inatools.backend.repository.UserConnectionRepository;
import inatools.backend.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserConnectionService {

    private final MemberRepository memberRepository;
    private final UserConnectionRepository userConnectionRepository;

    /**
     * 연결 요청
     */
    @Transactional
    public UserCareConnectionResponse createRequest(String loginId,
            UserCareConnectionRequest userCareConnectionRequest) {
        Member requestedMember = memberRepository.findByUserId(userCareConnectionRequest.requestedUserId())
                .orElseThrow(() -> new IllegalArgumentException("요청한 사용자를 찾을 수 없습니다."));

        Member requestingMember = memberRepository.findById(userCareConnectionRequest.memberId())
                .orElseThrow(() -> new IllegalArgumentException("요청 회원과 로그인 회원이 일치하지 않습니다."));
        if (!loginId.equals(requestingMember.getUserId())) {
            throw new IllegalArgumentException("요청 회원과 로그인 회원이 일치하지 않습니다.");
        }

        userConnectionRepository.findByRequestedMemberAndRequestingMember(requestedMember, requestingMember)
                .ifPresent(userCareConnection -> {
                    throw new IllegalArgumentException("이미 요청을 보낸 상태입니다.");
                });

        UserCareConnection userCareConnection =
                UserCareConnection.createUserConnection(requestedMember, requestingMember);
        userConnectionRepository.save(userCareConnection);

        return UserCareConnectionResponse.fromUserCareConnection(userCareConnection);
    }

    /**
     * 전체 조회
     */
    public UserCareConnectionListResponse getConnections(String loginId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 사용자를 찾을 수 없습니다."));
        if (!loginId.equals(member.getUserId())) {
            throw new IllegalArgumentException("요청 회원과 로그인 회원이 일치하지 않습니다.");
        }

        List<UserCareConnectionResponse> userCareConnectionListResponse =
                userConnectionRepository.findAllByRequestedMemberOrRequestingMember(member, member).stream()
                        .map(UserCareConnectionResponse::fromUserCareConnection)
                        .toList();

        return new UserCareConnectionListResponse(userCareConnectionListResponse);

    }

    /**
     * 연결 요청 응답
     */
    @Transactional
    public UserCareConnectionResponse replyToRequest(String loginId, Long userCareConnectionId,
            UserConnectionReplyRequest userCareConnectionRequest) {
        // 요청한 사용자 검증
        Member requestingMember = memberRepository.findById(userCareConnectionRequest.requestingMemberId())
                .orElseThrow(() -> new IllegalArgumentException("요청한 사용자를 찾을 수 없습니다."));

        // 요청 받은 사용자 검증
        Member requestedMember = memberRepository.findById(userCareConnectionRequest.requestedMemberId())
                .orElseThrow(() -> new IllegalArgumentException("요청 받은 사용자를 찾을 수 없습니다."));
        if (!loginId.equals(requestedMember.getUserId())) {
            throw new IllegalArgumentException("요청 받은 사용자와 로그인 사용자가 일치하지 않습니다.");
        }

        // 연결 요청 검증
        UserCareConnection userCareConnection = userConnectionRepository.findById(userCareConnectionId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 연결을 찾을 수 없습니다."));

        userCareConnection.replyToRequest(userCareConnectionRequest.replyStatus(), requestingMember, requestedMember);

        return UserCareConnectionResponse.fromUserCareConnection(userCareConnection);
    }

    /**
     * 연결 요청 취소
     */
    @Transactional
    public void cancelRequest(String loginId, Long userCareConnectionId) {
        UserCareConnection userCareConnection = userConnectionRepository.findById(userCareConnectionId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 연결을 찾을 수 없습니다."));

        Member requestingMember = userCareConnection.getRequestingMember();
        if (!loginId.equals(requestingMember.getUserId())) {
            throw new IllegalArgumentException("요청을 취소할 수 있는 권한이 없습니다.");
        }

        userConnectionRepository.delete(userCareConnection);
    }

    @Transactional
    public void disconnectConnection(String loginId, Long userCareConnectionId) {
        UserCareConnection userCareConnection = userConnectionRepository.findById(userCareConnectionId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 연결을 찾을 수 없습니다."));

        Member requestingMember = userCareConnection.getRequestingMember();
        Member requestedMember = userCareConnection.getRequestedMember();

        if (!loginId.equals(requestingMember.getUserId())
                && !loginId.equals(requestedMember.getUserId())) {
            throw new IllegalArgumentException("연결을 끊을 수 있는 권한이 없습니다.");
        }

        userConnectionRepository.delete(userCareConnection);
    }
}
