package me.kenux.playground.jpa.mapping;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.domain.Team;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
class ManyToOneTest {

    @Autowired
    EntityManager em;

    @Test
    void save() {
        Team team = new Team("teamA");
        em.persist(team);

        Member member1 = new Member("member1", 20);
        member1.assignTeam(team);
        em.persist(member1);

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member1.getId());
        assertThat(findMember).isNotNull();
        assertThat(findMember.getTeam().getName()).isEqualTo("teamA");
        log.info("findMember = {}", findMember);
    }

    @Test
    void update() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member member1 = new Member("member1", 20);
        member1.assignTeam(teamA);
        em.persist(member1);

        Team teamB = new Team("teamB");
        em.persist(teamB);

        Member findMember = em.find(Member.class, member1.getId());
        findMember.assignTeam(teamB);
        em.flush();
        em.clear();

        findMember = em.find(Member.class, member1.getId());
        assertThat(findMember.getTeam().getName()).isEqualTo("teamB");
    }

    @Test
    void deleteRelation() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member member1 = new Member("member1", 20);
        member1.assignTeam(teamA);
        em.persist(member1);
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member1.getId());
        findMember.assignTeam(null);
        em.flush();
        em.clear();

        findMember = em.find(Member.class, member1.getId());
        assertThat(findMember.getTeam()).isNull();
    }

    @Test
    void removeTeam_error() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member member1 = new Member("member1", 20);
        member1.assignTeam(teamA);
        em.persist(member1);

        Member findMember = em.find(Member.class, member1.getId());
        em.remove(findMember.getTeam());
        em.flush();
        assertThatThrownBy(() -> em.flush())
                .isInstanceOf(PersistenceException.class); // ConstraintViolationException
    }

    @Test
    void removeTeam_success() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member member1 = new Member("member1", 20);
        member1.assignTeam(teamA);
        em.persist(member1);

        Member findMember = em.find(Member.class, member1.getId());
        Team removeTeam = findMember.getTeam();
        findMember.assignTeam(null);
        em.remove(removeTeam);
        Assertions.assertDoesNotThrow(() -> em.flush());
    }
}
