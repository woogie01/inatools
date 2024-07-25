package inatools.backend.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue
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

}
