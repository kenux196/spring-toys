package me.kenux.jpabook.jpashop.repository;

import me.kenux.jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
//@DataJpaTest
class MemberRepositoryTest {
//
////    @PersistenceContext
////    EntityManager em;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
////    @Transactional
//    void testMember() {
//        // given
//        Member member = new Member();
//        member.setName("memberA");
//
//        // when
//        Member savedMember = memberRepository.save(member);
//
//        // then
//        Member findMember = memberRepository.findOne(savedMember.getId());
//        assertThat(findMember.getId()).isEqualTo(member.getId());
//        assertThat(findMember.getName()).isEqualTo(member.getName());
//        assertThat(findMember).isEqualTo(member);
//        System.out.println("findMember = " + findMember);
//        System.out.println("member = " + member);
//    }
}