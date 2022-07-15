package me.kenux.playground.jpa.web.dto;

import lombok.Data;
import me.kenux.playground.jpa.domain.Member;

@Data
public class MemberBasicResponse {

    private Long id;
    private String name;
    private Integer age;

    public MemberBasicResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
    }
}
