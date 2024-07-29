package inatools.backend.auth.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inatools.backend.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserDetailsDto {

    private Long id;

    @JsonIgnore
    private String userId;

    @JsonIgnore
    private String password;

    private String username;

    private List<String> role;

    public UserDetailsDto(Member member) {
        this.id = member.getId();
        this.userId = member.getUserId();
        this.password = member.getPassword().getValue();
        this.username = member.getUsername();
        this.role = List.of(member.getRole().getAuthority());
    }
}
