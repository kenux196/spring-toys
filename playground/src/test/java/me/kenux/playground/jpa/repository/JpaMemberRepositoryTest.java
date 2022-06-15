package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AUTO_CONFIGURED)
@SpringBootTest
class JpaMemberRepositoryTest {

    @Autowired
    JpaMemberRepository repository;

    @Test
    void save() {
        // given
        Member member = new Member("memberA", 39);

        // when
        repository.save(member);

        // then
        Optional<Member> findMember = repository.findById(member.getId());
        assertTrue(findMember.isPresent());
    }
}