package inatools.backend.domain;

import inatools.backend.dto.carereceiver.UserCareConnectionRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user_care_connections")
public class UserCareConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "care_receiver_id")
    private Long id;

    // 연결된 회원의 식별자
    private Long connectedMemberId;

    // 요청 상태
    @Enumerated(value = EnumType.STRING)
    private ConnectionStatus connectionStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected UserCareConnection() {}

    public UserCareConnection(Long connectedMemberId, ConnectionStatus connectionStatus, Member member) {
        this.connectedMemberId = connectedMemberId;
        this.connectionStatus = connectionStatus;
        this.member = member;
    }

    public static UserCareConnection createUserConnection(UserCareConnectionRequest userCareConnectionRequest, Member member) {
        return new UserCareConnection(
                userCareConnectionRequest.connectedMemberId(),
                ConnectionStatus.REQUESTED,
                member
        );
    }
}
