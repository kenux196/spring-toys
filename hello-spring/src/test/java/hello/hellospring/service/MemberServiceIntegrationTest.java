package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long savedId = memberService.join(member);

        // then
        Member result = memberRepository.findById(savedId).get();
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        // when
        memberService.join(member1);

        // then
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 존재하는 회원입니다.");

    }
}
