package me.kenux.springsecurity.web.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String name;
    private String role;

    public UserResponse(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
