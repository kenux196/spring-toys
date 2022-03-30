package me.kenux.jpalearn.domain.member.repository;

import me.kenux.jpalearn.domain.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaBatchTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void saveAllTest() {
        final List<Member> members = generateTestData();
        final long startTime = System.currentTimeMillis();
        memberRepository.saveAll(members);
        System.out.println("10000개 저장 시간 = " + (System.currentTimeMillis() - startTime));
    }

    @Test
    void saveAllAndFlushTest() {
        final List<Member> members = generateTestData();
        final long startTime = System.currentTimeMillis();
        memberRepository.saveAllAndFlush(members);
        System.out.println("10000개 저장 시간 = " + (System.currentTimeMillis() - startTime));
    }

    @Test
    void saveEach() {
        final List<Member> members = generateTestData();
        final long startTime = System.currentTimeMillis();
        members.forEach(member -> memberRepository.save(member));
        System.out.println("10000개 저장 시간 = " + (System.currentTimeMillis() - startTime));
    }


    private List<Member> generateTestData() {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            final Member member = Member.builder()
                    .name("member" + i)
                    .address("대구")
                    .age(40)
                    .phone("010-1234-1234")
                    .build();
            members.add(member);
        }
        return members;
    }
}