package me.kenux.springsecurity.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority implements GrantedAuthority {

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final Authority USER = new Authority(ROLE_USER);
    private static final Authority ADMIN = new Authority(ROLE_ADMIN);

    private String authority;
}