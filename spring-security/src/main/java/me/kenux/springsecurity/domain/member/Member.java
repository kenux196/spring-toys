package me.kenux.springsecurity.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Setter @Getter
@NoArgsConstructor
public class Member implements UserDetails {

    private Long id;
    private String name;

    private String password;

    private Set<Authority> authorities;

    private boolean enabled;

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}