package me.kenux.playground.jpa.repository;

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
    @DisplayName("다른 영속성 컨텍스트의 엔티티 비교 시, 동일성 비교는 실패한다. 동등성 비교를 해야 한다.")
    void join() {
        // given
        Member member = new Member("member1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepository.findOne(saveId);
        // findMember 는 준영속 상태

        // 둘은 다른 주소값을 가진 인스턴스다.
        Assertions.assertThat(member == findMember).isFalse();
    }
}
