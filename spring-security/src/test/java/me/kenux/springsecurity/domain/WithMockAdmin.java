package me.kenux.springsecurity.domain;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(value = "super", roles = "ADMIN")
public @interface WithMockAdmin {
}
