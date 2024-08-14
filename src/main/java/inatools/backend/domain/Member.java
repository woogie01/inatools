package inatools.backend.domain;


import inatools.backend.common.BaseTimeEntity;
import inatools.backend.dto.member.SelfCheckRequest;
import inatools.backend.dto.member.SignUpRequest;
import inatools.backend.dto.member.UpdateMemberRequest;
import inatools.backend.service.UserConnectionService;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;

import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Getter
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String userId;
    @Embedded
    private Password password;
    private String email;
    private String phone;

    /**
     * 자가 점검 데이터
     */
    private long gender;
    private LocalDate birthDate;
    private String underlyingDisease; // 기저질환
    private boolean familyHistory; // 가족력

    @Enumerated(value = EnumType.STRING)
    private SmokingStatus smokingStatus; // 흡연

    @Enumerated(value = EnumType.STRING)
    private DrinkingStatus drinkingStatus; // 음주

    @Enumerated(value = EnumType.STRING)
    private DangerStatus dangerStatus; // 뇌졸중 위험군

    private Role role;

    protected Member() {
    }

    // 회원 가입시 받는 필드값
    public Member(String username, String userId, Password password, String email, String phone) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = Role.USER;
    }

    // 모든 필드를 받는 생성자
    public Member(String username, String userId, Password password, String email, String phone, Long gender,
            LocalDate birthDate,
            String underlyingDisease, boolean familyHistory, SmokingStatus smokingStatus, DrinkingStatus drinkingStatus
            , DangerStatus dangerStatus
    ) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.underlyingDisease = underlyingDisease;
        this.familyHistory = familyHistory;
        this.smokingStatus = smokingStatus;
        this.drinkingStatus = drinkingStatus;
        this.dangerStatus = dangerStatus;
    }

    // 회원 생성 메서드
    public static Member createMember(SignUpRequest signUpRequest, PasswordEncoder passwordEncoder) {

        return new Member(
                signUpRequest.username(),
                signUpRequest.userId(),
                Password.encrypt(signUpRequest.password(), passwordEncoder),
                signUpRequest.email(),
                signUpRequest.phone()
        );
    }

    // 인스턴스 메서드: 회원 정보 업데이트
    public void updateMemberInfo(UpdateMemberRequest updateRequest, PasswordEncoder passwordEncoder) {

        this.username = updateRequest.name();
        this.userId = updateRequest.userId();
        this.password = Password.encrypt(updateRequest.password(), passwordEncoder);
        this.phone = updateRequest.phone();
    }

    // 기본 자가점검 정보 업데이트
    public void updateBasicSelfCheck(SelfCheckRequest request) {
        this.gender = request.gender();
        this.birthDate = request.birthDate();
        this.underlyingDisease = request.underlyingDisease();
        this.familyHistory = request.familyHistory();
        this.smokingStatus = request.smokingStatus();
        this.drinkingStatus = request.drinkingStatus();
        this.dangerStatus = request.dangerStatus();
    }

}
