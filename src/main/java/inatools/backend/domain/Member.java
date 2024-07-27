package inatools.backend.domain;


import inatools.backend.dto.member.SignUpRequest;
import inatools.backend.dto.member.UpdateMemberRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String userId;
    private String password;
    private String email;
    private String phone;

    private long gender;
    private long age;
    private String underlyingDisease; // 기저질환
    private boolean familyHistory; // 가족력
    private SmokingStatus smokingStatus; // 흡연
    private DrinkingStatus drinkingStatus; // 음주

    private String role;

    public Member() {
    }

    // 회원 가입시 받는 필드값
    public Member(String username, String userId, String password, String email, String phone, String role) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // 모든 필드를 받는 생성자
    public Member(String username, String userId, String password, String email, String phone, Long gender, Long age,
            String underlyingDisease, boolean familyHistory, SmokingStatus smokingStatus, DrinkingStatus drinkingStatus, String medication) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.underlyingDisease = underlyingDisease;
        this.familyHistory = familyHistory;
        this.smokingStatus = smokingStatus;
        this.drinkingStatus = drinkingStatus;
    }

    public Member(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // 회원 생성 메서드
    public static Member createMember(SignUpRequest signUpRequest, PasswordEncoder passwordEncoder) {

        return new Member(
                signUpRequest.username(),
                signUpRequest.userId(),
                passwordEncoder.encode(signUpRequest.password()),
                signUpRequest.email(),
                signUpRequest.phone(),
                "ROLE_ADMIN"
        );
    }

    // 인스턴스 메서드: 회원 정보 업데이트
    public void updateMemberInfo(UpdateMemberRequest updateRequest, PasswordEncoder passwordEncoder) {

        this.username = updateRequest.name();
        this.userId = updateRequest.userId();
        this.password = passwordEncoder.encode(updateRequest.password());
        this.phone = updateRequest.phone();
    }

}
