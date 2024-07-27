package inatools.backend.domain;


import inatools.backend.dto.SignUpRequest;
import inatools.backend.dto.UpdateMemberRequest;
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

    private String name;
    private String userId;
    private String password;
    private String email;
    private String phone;

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
    public Member(String name, String userId, String password, String email, String phone) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    // 모든 필드를 받는 생성자
    public Member(String name, String userId, String password, String email, String phone, Long gender, Long age,
            String underlyingDisease, boolean familyHistory, boolean isSmoker, boolean isDrinker, String medication) {
        this.name = name;
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
                signUpRequest.name(),
                signUpRequest.userId(),
                encodedPassword,
                signUpRequest.email(),
                signUpRequest.phone()
        );
    }

    // 인스턴스 메서드: 회원 정보 업데이트
    public void updateMemberInfo(UpdateMemberRequest updateRequest) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(updateRequest.password());

        this.name = updateRequest.name();
        this.userId = updateRequest.userId();
        this.password = encodedPassword;
        this.phone = updateRequest.phone();
    }

}
