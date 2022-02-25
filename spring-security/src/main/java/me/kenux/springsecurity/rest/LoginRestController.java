package me.kenux.springsecurity.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginRestController {

    @PostMapping("/login")
    public ResponseEntity<?> login(Authentication authentication) {
        log.debug("api login => {}", authentication.getName());
        return ResponseEntity.ok("반갑습니다. " + authentication.getName() + "님");
    }

}