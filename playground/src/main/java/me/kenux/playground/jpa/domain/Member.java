package me.kenux.playground.jpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void assignTeam(Team team) {
        this.team = team;
    }
}
