package me.kenux.playground.jpa.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@EqualsAndHashCode
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    private Integer age;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private final List<Order> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public void joinTeam(Team team) {
        if (team == null) {
            throw new InvalidParameterException("팀 설정에 null 값이 입력되었습니다.");
        }
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }

    public void removeTeam() {
        if (this.team != null) {
            this.team.getMembers().remove(this);
            this.team = null;
        }
    }


    public void joinCompany(Company company) {
        this.company = company;
    }

    public Member(String name) {
        this.name = name;
    }

    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void changeAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != null ? !id.equals(member.id) : member.id != null) return false;
        return name != null ? name.equals(member.name) : member.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
