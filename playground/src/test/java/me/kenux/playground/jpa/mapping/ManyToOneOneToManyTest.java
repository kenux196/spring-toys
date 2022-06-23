package me.kenux.playground.jpa.mapping;

import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
@DisplayName("다대일 - 일대다 양방향 연관관계 테스트")
class ManyToOneOneToManyTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        Team team = new Team("TeamA");
        em.persist(team);
        Member member1 = new Member("memberA");
        member1.setTeam(team);
        em.persist(member1);
        Member member2 = new Member("memberB");
        member2.setTeam(team);
        em.persist(member2);
        em.flush();
        em.clear();
    }

    @Test
    void biDirectionTest() {
        Team findTeam = em.find(Team.class, 1L);
        List<Member> members = findTeam.getMembers();

        assertThat(members).hasSize(2);
        for (Member member : members) {
            System.out.println("member = " + member);
            ;
        }
    }

    @Test
    @DisplayName("연관관계 주인이 아닌 곳에 입력된 값은 외래 키에 영향을 주지 않는다.")
    void owner_failed() {
        Member newMember = new Member("memberB");
        em.persist(newMember);

        Team findTeam = em.find(Team.class, 1L);
        findTeam.getMembers().add(newMember); // 무시(연관관계의 주인이 아님)
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, newMember.getId());
        assertThat(findMember.getTeam()).isNull();
    }

    @Test
    @DisplayName("순수한 객체의 양방향 -> 한쪽만 연결한 경우 반대쪽 조회 실패")
    void pureObjectBiDirection_fail() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        Team team = new Team("teamA");

        // Member 쪽에서만 관계 설정
        member1.setTeam(team);
        member2.setTeam(team);

        // 관계 설정되지 않은 Team 쪽에서 Member 찾으면? member 수는 0
        assertThat(team.getMembers()).isEmpty();
    }

    @Test
    @DisplayName("순수한 객체의 양방향 -> 양쪽 모두 연결한 경우 양쪽 모두 조회 가능")
    void pureObjectBiDirection_success() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        Team team = new Team("teamA");

        // Member 쪽에서만 관계 설정
        member1.setTeam(team);
//        team.getMembers().add(member1);
        member2.setTeam(team);
//        team.getMembers().add(member2);

        assertThat(member1.getTeam()).isNotNull();
        assertThat(team.getMembers()).hasSize(2);
    }

    @Test
    @DisplayName("JPA를 사용한 양방향 연관관계 완성")
    void ORM_BiDirection() {
        Team team = new Team("teamB");
        em.persist(team);

        Member member1 = new Member("member1");
        member1.setTeam(team);
        team.getMembers().add(member1);
        em.persist(member1);

        Member member2 = new Member("member2");
        member2.setTeam(team);
        team.getMembers().add(member2);
        em.persist(member2);

        em.flush();
        em.clear();

        Team findTeam = em.find(Team.class, team.getId());
        assertThat(findTeam.getMembers()).hasSize(2);
    }

    @Test
    @DisplayName("연관관계 편의 메소드 사용")
    void 연관관계_편의_메소드_사용() {
        Team team = new Team("teamB");
        em.persist(team);

        Member member1 = new Member("member1");
        member1.assignTeam(team);
        em.persist(member1);
        em.flush();
        em.clear();

        Team findTeam = em.find(Team.class, team.getId());
        assertThat(findTeam.getMembers()).isNotEmpty();
    }
}
