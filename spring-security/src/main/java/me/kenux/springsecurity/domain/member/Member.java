package me.kenux.springsecurity.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class Member {

    private Long id;
    private String name;
    private Integer age;


    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}