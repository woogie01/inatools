package inatools.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@ToString
public class Password {

    private static final int REPEATING_LIMIT = 3;
    private static final int CONSECUTIVE_LIMIT = 3;

    @Column(name = "password", nullable = false)
    private String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password encrypt(String value, PasswordEncoder encoder) {
        return new Password(encoder.encode(value));
    }

    public boolean isSamePassword(String password, PasswordEncoder encoder) {
        return encoder.matches(password, this.value);
    }
}
