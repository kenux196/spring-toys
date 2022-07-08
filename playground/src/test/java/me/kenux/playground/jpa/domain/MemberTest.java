package me.kenux.playground.jpa.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("회원이 가입하는 팀에 null 입력 불가능해야 한다.")
    void joinTeam_null_exception() {
        // given
        final Member member = new Member("회원1", new Address("대구", "달구벌대로", "123"));

        // when, then
        assertThatThrownBy(() -> member.joinTeam(null))
                .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    @DisplayName("양방향 연관관계 편의 메소드 테스트 - 양쪽에서 모두 조회 가능해야 한다.")
    void joinTeam_success() {
        // given
        final Member member = new Member("회원1", new Address("대구", "달구벌대로", "123"));
        Team team = new Team("팀A");

        // when
        member.joinTeam(team);

        // then
        assertThat(member.getTeam().getName()).isEqualTo("팀A");
        assertThat(team.getMembers()).contains(member);
    }
}