package me.kenux.playground.jpa.mapping;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
class ManyToOneTest {

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void save() {
        Team team = new Team("teamA");
        em.persist(team);

        Member member1 = new Member("member1", 20);
        em.persist(member1);
        member1.assignTeam(team);

        em.flush();
        em.close();

        Member findMember = em.find(Member.class, member1.getId());
        assertThat(findMember).isNotNull();
        assertThat(findMember.getTeam().getName()).isEqualTo("teamA");
        log.info("findMember = {}", findMember);
    }
}
