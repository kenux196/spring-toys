package me.kenux.springsecurity.domain.member;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER")
    ;

    Role(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
