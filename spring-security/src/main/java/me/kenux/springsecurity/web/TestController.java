package me.kenux.springsecurity.web;

import me.kenux.springsecurity.web.dto.SecurityMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public SecurityMessage admin() {
        return SecurityMessage.builder()
                .message("admin page")
                .authentication(SecurityContextHolder.getContext().getAuthentication())
                .build();
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/user")
    public SecurityMessage user() {
        return SecurityMessage.builder()
                .message("user page")
                .authentication(SecurityContextHolder.getContext().getAuthentication())
                .build();
    }
}