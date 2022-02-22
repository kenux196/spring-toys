package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    void setup() {
        memberRepository.clear();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).orElse(null);

        assertThat(result).isNotNull()
                .isEqualTo(member);
        assertThat(result.getId()).isEqualTo(member.getId());
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Optional<Member> result = memberRepository.findByName("spring1");

        assertThat(result).isNotEmpty()
                .get()
                .isEqualTo(member1);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result).hasSize(2);
    }
}