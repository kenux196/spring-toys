package me.kenux.jpalearn.domain.member.repository;

import me.kenux.jpalearn.domain.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        // given
        Member member = Member.builder()
                .name("kenux")
                .address("대구")
                .age(40)
                .phone("010-3950-1961")
                .build();

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(savedMember.getId()).isEqualTo(1L);
    }

    @Test
    void findById() {
        // given
        Member member = Member.builder()
                .name("kenux")
                .address("대구")
                .age(40)
                .phone("010-3950-1961")
                .build();

        memberRepository.save(member);

        // when
        Optional<Member> findMember = memberRepository.findById(member.getId());

        // then
        assertThat(findMember).isNotEmpty()
                .get().extracting(Member::getId).isEqualTo(member.getId());
        assertThat(findMember.get().getId()).isEqualTo(member.getId());
    }
}