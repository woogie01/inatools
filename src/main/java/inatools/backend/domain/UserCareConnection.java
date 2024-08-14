package inatools.backend.domain;

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

    // 요청 상태
    @Enumerated(value = EnumType.STRING)
    private ConnectionStatus connectionStatus;

    // 연결 요청을 받은 회원의 식별자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_member_id")
    private Member requestedMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requesting_member_id")
    private Member requestingMember;

    protected UserCareConnection() {}

    public UserCareConnection(ConnectionStatus connectionStatus, Member requestedMember, Member requestingMember) {
        this.connectionStatus = connectionStatus;
        this.requestedMember = requestedMember;
        this.requestingMember = requestingMember;
    }

    public static UserCareConnection createUserConnection(Member requestedMember, Member requestingMember) {
        return new UserCareConnection(
                ConnectionStatus.REQUESTING,
                requestedMember,
                requestingMember
        );
    }
}
