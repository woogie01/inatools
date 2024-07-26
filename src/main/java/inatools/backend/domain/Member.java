package inatools.backend.domain;


import inatools.backend.dto.SignUpRequest;
import inatools.backend.dto.UpdateMemberRequest;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Authority authority; // 권한 정보

    private long gender;
    private long age;
    private String underlyingDisease; // 기저질환
    private boolean familyHistory; // 가족력
    private boolean isSmoker; // 흡연
    private boolean isDrinker; // 음주
    private String medication; // 복용약

    public Member() {
    }

    // 회원 가입시 받는 필드값
    public Member(String username, String userId, String password, String email, String phone, Authority authority) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.authority = authority;
    }

    // 모든 필드를 받는 생성자
    public Member(String username, String userId, String password, String email, String phone, Long gender, Long age,
            String underlyingDisease, boolean familyHistory, boolean isSmoker, boolean isDrinker, String medication) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.underlyingDisease = underlyingDisease;
        this.familyHistory = familyHistory;
        this.isSmoker = isSmoker;
        this.isDrinker = isDrinker;
        this.medication = medication;
    }


    // 회원 생성 메서드
    public static Member createMember(SignUpRequest signUpRequest) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(signUpRequest.password());

        return new Member(
                signUpRequest.username(),
                signUpRequest.userId(),
                encodedPassword,
                signUpRequest.email(),
                signUpRequest.phone(),
                Authority.USER // 권한 정보 : 사용자 OR 관리자 지정
        );
    }

    // 인스턴스 메서드: 회원 정보 업데이트
    public void updateMemberInfo(UpdateMemberRequest updateRequest) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(updateRequest.password());

        this.username = updateRequest.username();
        this.userId = updateRequest.userId();
        this.password = encodedPassword;
        this.phone = updateRequest.phone();
    }

}
