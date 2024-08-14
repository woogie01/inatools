package inatools.backend.service;

import inatools.backend.domain.UserCareConnection;
import inatools.backend.domain.Member;
import inatools.backend.dto.userconnection.UserCareConnectionListResponse;
import inatools.backend.dto.userconnection.UserCareConnectionRequest;
import inatools.backend.dto.userconnection.UserCareConnectionResponse;
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

        UserCareConnection userCareConnection =
                UserCareConnection.createUserConnection(requestedMember, requestingMember);

        return UserCareConnectionResponse.fromUserCareConnection(userCareConnection);
    }

    public UserCareConnectionListResponse getConnections(String loginId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("요청 회원과 로그인 회원이 일치하지 않습니다."));
        Member.checkMember(loginId, member);

        List<UserCareConnectionResponse> userCareConnectionListResponse =
                userConnectionRepository.findAllByMemberId(member.getId()).stream()
                        .map(UserCareConnectionResponse::fromUserCareConnection)
                        .toList();

        return new UserCareConnectionListResponse(userCareConnectionListResponse);

    }

    public UserCareConnectionResponse responseRequest(String loginId,
            UserCareConnectionRequest userCareConnectionRequest) {
        Member requestedMember = memberRepository.findByUserId(userCareConnectionRequest.requestedUserId())
                .orElseThrow(() -> new IllegalArgumentException("요청한 사용자를 찾을 수 없습니다."));

        Member requestingMember = memberRepository.findById(userCareConnectionRequest.memberId())
                .orElseThrow(() -> new IllegalArgumentException("요청 회원과 로그인 회원이 일치하지 않습니다."));
        Member.checkMember(loginId, requestingMember);
    }
}
