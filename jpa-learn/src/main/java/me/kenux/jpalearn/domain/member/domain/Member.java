package me.kenux.jpalearn.domain.member.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = "team")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // batch insert 동작하지 않는다.
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // batch insert 동작한다.
//    @GeneratedValue(strategy = GenerationType.TABLE) // 성능 가장 안좋다
    private Long id;

    private String name;

    private String phone;

    private String address;

    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Member(String name, String phone, String address, Integer age) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    public void changeTeam(Team team) {
        this.team = team;
    }
}
