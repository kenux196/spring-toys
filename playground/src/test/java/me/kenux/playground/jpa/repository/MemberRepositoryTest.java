package me.kenux.playground.jpa.repository;

import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.repository.dto.MemberSearchCond;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        // given
        Member member = new Member("memberA");

        // when
        memberRepository.save(member);

        // then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        Assertions.assertTrue(findMember.isPresent());
    }

    @Test
    @DisplayName("이름에 member가 포함된 회원 검색: 성공")
    void findBySearchConditionWithName_success() {
        // given
        Member memberA = new Member("memberA");
        Member memberB = new Member("memberB");
        Member memberC = new Member("memberC");

        memberRepository.saveAll(Arrays.asList(memberA, memberB, memberC));

        MemberSearchCond searchCond = new MemberSearchCond();
        searchCond.setName("member");

        // when
        List<Member> result = memberRepository.findAllByCondition(searchCond);

        // then
        assertThat(result).hasSize(3);
    }

    @Test
    @DisplayName("이름에 member가 포함되고 나이가 30이하인 회원 검색: 성공")
    void findBySearchCondWithNameAndAge_success() {
        // given
        Member memberA = new Member("memberA");
        Member memberB = new Member("memberB");
        Member memberC = new Member("memberC");

        memberRepository.saveAll(Arrays.asList(memberA, memberB, memberC));

        MemberSearchCond searchCond = new MemberSearchCond();
        searchCond.setName("member");
        searchCond.setAge(30);

        // when
        List<Member> result = memberRepository.findAllByCondition(searchCond);

        // then
        assertThat(result).hasSize(2);
    }
}