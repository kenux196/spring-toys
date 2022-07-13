package me.kenux.playground.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.service.JpaMemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Import(QuerydslConfig.class)
//@Transactional
class JpaTest {

    @Autowired
    EntityManager em;
    @Autowired
    JpaMemberRepository memberRepository;
    @Autowired
    JpaMemberService memberService;

    @Test
    @DisplayName("동일한 영속성 컨텍스트의 엔티티 비교 시, 동일성(==) 비교 성공한다.")
    @Transactional
    void compareEntity1() {
        // given
        Member member = new Member("member1");

        // when
        Long saveId = memberService.join(member);
        log.info("======= service join end ======");

        // then
        // Participating in existing transaction => 트랜잭션 전파.
        Member findMember = memberRepository.findOne(saveId);
        log.info("======= repository find one end ======");
        // findMember 는 준영속 상태

        // 둘은 다른 주소값을 가진 인스턴스다.
        assertThat(member).isSameAs(findMember);
    }

    @Test
    @DisplayName("다른 영속성 컨텍스트의 엔티티 비교 시, 동일성(==) 비교는 실패한다. 동등성 비교를 해야 한다.")
    void compareEntity2() {
        // given
        Member member = new Member("member1");

        // 아래 둘은 서로 다른 트랜잭션으로 수행된다.
        // when
        Long saveId = memberService.join(member);
        log.info("======= service join end ======");

        // then
        Member findMember = memberRepository.findOne(saveId);
        log.info("======= repository find one end ======");
        // findMember 는 준영속 상태

        // 둘은 다른 주소값을 가진 인스턴스다.
        assertThat(member).isNotSameAs(findMember);
    }

    @Test
    @DisplayName("영속성 컨텍스트와 프록시1 - 프록시 조회 후 원본 엔티티 조회 시 동일성 보장")
    @Transactional
    void persistenceContextAndProxy1() {
        Member newMember = new Member("member1");
        em.persist(newMember);
        em.flush();
        em.clear();

        Member refMember = em.getReference(Member.class, newMember.getId());
        Member findMember = em.find(Member.class, newMember.getId());

        log.info("refMember Type = {}", refMember.getClass());
        log.info("findMember Type = {}", findMember.getClass());

        assertThat(refMember).isSameAs(findMember);
    }

    @Test
    @DisplayName("영속성 컨텍스트와 프록시2 - 원본 엔티티로 조회 후 프록시 조회 시 동일성 보장")
    @Transactional
    void persistenceContextAndProxy2() {
        Member newMember = new Member("member1");
        em.persist(newMember);
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, newMember.getId());
        Member refMember = em.getReference(Member.class, newMember.getId());

        log.info("refMember Type = {}", refMember.getClass());
        log.info("findMember Type = {}", findMember.getClass());

        assertThat(refMember).isSameAs(findMember);
    }

    @Test
    @DisplayName("프록시 타입 비교 예제")
    @Transactional
    void compareProxyType() {
        Member newMember = new Member("member1");
        em.persist(newMember);
        em.flush();
        em.clear();

        Member refMember = em.getReference(Member.class, newMember.getId());
        log.info("refMember Type = {}", refMember.getClass());

        assertThat(Member.class).isNotSameAs(refMember.getClass());
        assertThat(refMember).isInstanceOf(Member.class);
    }

    @Test
    @DisplayName("프록시 동등성 비교")
    @Transactional
    void proxyEquals() {
        Member saveMember = new Member("member1");
        em.persist(saveMember);
        em.flush();
        em.clear();

        Member newMember = new Member("member1");
        Member refMember = em.getReference(Member.class, saveMember.getId());

        assertThat(newMember.equals(refMember)).isTrue();
    }
}
