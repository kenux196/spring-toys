package me.kenux.playground.jpa.mapping;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.domain.Team;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("다대일 단방향 연관관계 테스트")
class ManyToOneTest {

    @Autowired
    EntityManager em;

    @Test
    @Order(1)
    void save() {
        Team team = new Team("teamA");
        em.persist(team);

        Member member1 = new Member("member1");
        member1.setTeam(team);
        em.persist(member1);

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member1.getId());
        assertThat(findMember).isNotNull();
        assertThat(findMember.getTeam().getName()).isEqualTo("teamA");
        log.info("findMember = {}", findMember);
    }

    @Test
    @Order(2)
    void update() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member member1 = new Member("member1");
        member1.setTeam(teamA);
        em.persist(member1);

        Team teamB = new Team("teamB");
        em.persist(teamB);

        Member findMember = em.find(Member.class, member1.getId());
        findMember.setTeam(teamB);
        em.flush();
        em.clear();

        findMember = em.find(Member.class, member1.getId());
        assertThat(findMember.getTeam().getName()).isEqualTo("teamB");
    }

    @Test
    @Order(3)
    void deleteRelation() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member member1 = new Member("member1");
        member1.setTeam(teamA);
        em.persist(member1);
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member1.getId());
        findMember.setTeam(null);
        em.flush();
        em.clear();

        findMember = em.find(Member.class, member1.getId());
        assertThat(findMember.getTeam()).isNull();
    }

    @Test
    @Order(4)
    void removeTeam_error() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member member1 = new Member("member1");
        member1.setTeam(teamA);
        em.persist(member1);

        Member findMember = em.find(Member.class, member1.getId());
        em.remove(findMember.getTeam());
        assertThatThrownBy(() -> em.flush())
                .isInstanceOf(PersistenceException.class); // ConstraintViolationException
    }

    @Test
    @Order(5)
    void removeTeam_success() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member member1 = new Member("member1");
        member1.setTeam(teamA);
        em.persist(member1);

        Member findMember = em.find(Member.class, member1.getId());
        Team removeTeam = findMember.getTeam();
        findMember.setTeam(null);
        em.remove(removeTeam);
        Assertions.assertDoesNotThrow(() -> em.flush());
    }
}
