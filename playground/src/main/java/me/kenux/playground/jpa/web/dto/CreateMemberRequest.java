package me.kenux.playground.jpa.web.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CreateMemberRequest {

    private String name;
    private OffsetDateTime createdDate;
}
