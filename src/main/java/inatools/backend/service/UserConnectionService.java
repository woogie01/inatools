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

    @Transactional
    public UserCareConnectionResponse createRequest(String loginId,
            UserCareConnectionRequest userCareConnectionRequest) {
        Member requestedMember = memberRepository.findByUserId(userCareConnectionRequest.requestedUserId())
                .orElseThrow(() -> new IllegalArgumentException("요청한 사용자를 찾을 수 없습니다."));

        Member requestingMember = memberRepository.findById(userCareConnectionRequest.memberId())
                .orElseThrow(() -> new IllegalArgumentException("요청 회원과 로그인 회원이 일치하지 않습니다."));
        Member.checkMember(loginId, requestingMember);

        userConnectionRepository.findByRequestedMemberAndRequestingMember(requestedMember, requestingMember)
                .ifPresent(userCareConnection -> {
                    throw new IllegalArgumentException("이미 요청을 보낸 상태입니다.");
                });

        UserCareConnection userCareConnection =
                UserCareConnection.createUserConnection(requestedMember, requestingMember);
        userConnectionRepository.save(userCareConnection);

        return UserCareConnectionResponse.fromUserCareConnection(userCareConnection);
    }

    public UserCareConnectionListResponse getConnections(String loginId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("요청 회원과 로그인 회원이 일치하지 않습니다."));
        Member.checkMember(loginId, member);

        List<UserCareConnectionResponse> userCareConnectionListResponse =
                userConnectionRepository.findAllByRequestedMemberOrRequestingMember(member, member).stream()
                        .map(UserCareConnectionResponse::fromUserCareConnection)
                        .toList();

        return new UserCareConnectionListResponse(userCareConnectionListResponse);

    }

    @Transactional
    public UserCareConnectionResponse replyToRequest(String loginId, Long userCareConnectionId,
            UserConnectionReplyRequest userCareConnectionRequest) {
        // 요청한 사용자 검증
        Member requestingMember = memberRepository.findById(userCareConnectionRequest.requestingMemberId())
                .orElseThrow(() -> new IllegalArgumentException("요청한 사용자를 찾을 수 없습니다."));

        // 요청 받은 사용자 검증
        Member requestedMember = memberRepository.findById(userCareConnectionRequest.requestedMemberId())
                .orElseThrow(() -> new IllegalArgumentException("요청 받은 사용자를 찾을 수 없습니다."));
        Member.checkMember(loginId, requestedMember);

        // 연결 요청 검증
        UserCareConnection userCareConnection = userConnectionRepository.findById(userCareConnectionId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 연결을 찾을 수 없습니다."));

        userCareConnection.replyToRequest(userCareConnectionRequest.replyStatus(), requestingMember, requestedMember);

        return UserCareConnectionResponse.fromUserCareConnection(userCareConnection);
    }
}
