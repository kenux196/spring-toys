package me.kenux.springsecurity.domain;

import org.springframework.security.access.prepost.PreAuthorize;

public interface MessageService {

    @PreAuthorize(value = "authenticated")
    String getMessage();
}
