package me.kenux.playground.jpa.service;

import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Address;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(QuerydslConfig.class)
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    private static final int ALL_MEMBER_COUNT = 10;

    @BeforeEach
    void beforeEach() {
        generateTestData();
    }

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("전체 회원 조회")
    void getMembers() {
        List<Member> members = memberService.getMembers();
        assertThat(members).hasSize(ALL_MEMBER_COUNT);
    }

    @Test
    @DisplayName("회원 가입 - 회원 중복으로 실패")
    void joinMemberFailed() {
        // given
        Member member = new Member("회원1", new Address("대구", "도로", "123123"));

        // when then
        assertThatThrownBy(() -> memberService.join(member))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 회원입니다.");
    }

    @Test
    @DisplayName("회원 가입 - 성공")
    void joinMemberSuccess() {
        // given
        Member member = new Member("신규회원", new Address("대구", "도로", "123123"));

        // when
        Long result = memberService.join(member);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("회원 1명 조회 성공")
    void getMemberSuccess() {
        // given
        Long id = memberRepository.findAll().get(0).getId();

        // when
        Member member = memberService.getMember(id);
        assertThat(member.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("회원 1명 조회 실패 - 예외 발생")
    void getMemberFailed() {
        // given
        Long id = 0L;

        assertThatThrownBy(() -> memberService.getMember(id))
                .isInstanceOf(NoSuchElementException.class);
    }

    private void generateTestData() {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < ALL_MEMBER_COUNT; i++) {
            Member member = new Member("회원" + i, new Address("대구", "도로명", "1123"));
            members.add(member);
        }
        memberRepository.saveAll(members);
    }

}
