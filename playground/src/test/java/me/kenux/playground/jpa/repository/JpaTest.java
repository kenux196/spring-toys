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
        Assertions.assertThat(member ).isSameAs(findMember);
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
        Assertions.assertThat(member).isNotSameAs(findMember);
    }
}
