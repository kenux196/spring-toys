package me.kenux.springsecurity.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository = new MemberRepository();


    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @DisplayName("멤버 저장 테스트")
    @Test
    void save() {
        // given
        final Member member = new Member("user1", "password", Role.USER);

        // when
        final Member save = memberRepository.save(member);

        // then
        final Optional<Member> find = memberRepository.findById(save.getId());
        assertThat(find).isNotEmpty();
        assertThat(find.get().getId()).isEqualTo(save.getId());
    }

    @Test
    @DisplayName("멤버 아이디는 중복이 되면 안된다.")
    void test_member_must_not_be_duplicated() throws Exception {
        // given
        final Member member = new Member("user1", "password", Role.USER);
        final Member save = memberRepository.save(member);
        save.setName("test");

        // when
        final Member saved2 = memberRepository.save(save);

        // then
        final List<Member> members = memberRepository.findAll();
        assertThat(members).hasSize(1);
        assertThat(members.get(0).getUsername()).isEqualTo("test");
    }


}