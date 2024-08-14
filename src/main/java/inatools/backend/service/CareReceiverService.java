package inatools.backend.service;

import inatools.backend.domain.UserCareConnection;
import inatools.backend.domain.Member;
import inatools.backend.dto.carereceiver.UserCareConnectionRequest;
import inatools.backend.dto.carereceiver.UserCareConnectionResponse;
import inatools.backend.repository.CareReceiverRepository;
import inatools.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CareReceiverService {

    private final MemberRepository memberRepository;
    private final CareReceiverRepository careReceiverRepository;

    @Transactional
    public UserCareConnectionResponse createRequest(String loginId, UserCareConnectionRequest userCareConnectionRequest) {
        Member member = memberRepository.findByUserId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Member.checkMember(loginId, member);

        UserCareConnection userCareConnection = UserCareConnection.createUserConnection(userCareConnectionRequest, member);

        return UserCareConnectionResponse.fromCareReceiver(userCareConnection);
    }
}
