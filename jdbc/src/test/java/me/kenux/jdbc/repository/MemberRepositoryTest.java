package me.kenux.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.jdbc.domain.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class MemberRepositoryTest {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV2", 10000);
        repository.save(member);

        // findById
        Member findMember = repository.findById(member.getMemberId());
        assertThat(findMember).isEqualTo(member);
        log.info("findMember={}", findMember);
        log.info("findMember == member : {}", findMember == member);
        log.info("findMember equals member : {}", findMember.equals(member));
    }

    @Test
    void findById() throws SQLException {
        Member member = repository.findById("memberV0");
        assertThat(member).isNotNull();
        assertThat(member.getMemberId()).isEqualTo("memberV0");
        assertThat(member.getMoney()).isEqualTo(10000);

        log.info("find member={}", member);
    }

    @Test
    void findById_not_founded() throws SQLException {
        assertThatThrownBy(() -> repository.findById("member00"))
                .isInstanceOf(NoSuchElementException.class);
    }
}
