package me.kenux.springsecurity.securitycontextholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class SecurityContextHolderTest {


    @BeforeEach
    void settingSecurityContextHolder() {
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        final Authentication authentication =
                new TestingAuthenticationToken(
                        "userA", "password1004!", "ROLE_USER", "ROLE_ADMIN");
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    @Test
    void accessCurrentlyAuthenticatedUser() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();
        final String username = authentication.getName();
        final Object principal = authentication.getPrincipal();
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("username = " + username);
        System.out.println("principal = " + principal);
        System.out.println("authorities = " + authorities);
        System.out.println("authentication.getCredentials() = " + authentication.getCredentials());
    }
}