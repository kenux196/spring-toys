package me.kenux.jpabook.jpashop.service;

import me.kenux.jpabook.jpashop.domain.Member;
import me.kenux.jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void joinMember() {
        // given
        Member member = new Member();
        member.setName("kenux");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void duplicateMemberException() {
        // given
        Member member1 = new Member();
        member1.setName("kenux");

        Member member2 = new Member();
        member2.setName("kenux");

        // when
        memberService.join(member1);

        // then
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 존재하는 회원입니다.");

    }
}