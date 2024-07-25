package inatools.backend.domain;


import inatools.backend.dto.SignUpRequest;
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
    private String password;
    private long gender;
    private long age;
    private String phone;
    private String underlyingDisease; // 기저질환
    private boolean familyHistory; // 가족력
    private boolean isSmoker; // 흡연
    private boolean isDrinker; // 음주
    private String medication; // 복용약

    public Member() {
    }

    // 모든 필드를 받는 생성자
    public Member(String name, String password, Long gender, Long age, String phone,
            String underlyingDisease, boolean familyHistory, boolean isSmoker, boolean isDrinker,
            String medication) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.underlyingDisease = underlyingDisease;
        this.familyHistory = familyHistory;
        this.isSmoker = isSmoker;
        this.isDrinker = isDrinker;
        this.medication = medication;
    }


    // 정적 생성 메서드
    public static Member createMember(SignUpRequest signUpRequest) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(signUpRequest.password());

        return new Member(
                signUpRequest.name(),
                encodedPassword,
                signUpRequest.gender(),
                signUpRequest.age(),
                signUpRequest.phone(),
                signUpRequest.underlyingDisease(),
                signUpRequest.familyHistory(),
                signUpRequest.isSmoker(),
                signUpRequest.isDrinker(),
                signUpRequest.medication()
        );
    }

}
