package inatools.backend.auth.security.domain;

import inatools.backend.auth.security.dto.UserDetailsDto;
import inatools.backend.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final UserDetailsDto member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        addRoles(authorities);
        return authorities;
    }

    private void addRoles(Collection<GrantedAuthority> authorities) {
        member.getRole()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .forEach(authorities::add);
    }

    @Override
    public String getUsername() { // 아이디 검증을 위해 받아오기
        return member.getUserId();
    }

    @Override
    public String getPassword() { // 비밀번호 검증을 위해 받아오기
        return member.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
