package me.kenux.jpalearn.domain.member.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String address;

    private Integer age;

    @Builder
    public Member(String name, String phone, String address, Integer age) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }
}
