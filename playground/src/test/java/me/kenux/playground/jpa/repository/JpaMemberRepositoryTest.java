package me.kenux.playground.jpa.repository;

import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(QuerydslConfig.class)
class JpaMemberRepositoryTest {

    @Autowired
    EntityManager em;
    JpaMemberRepository repository;

    @PostConstruct
    public void init() {
        repository = new JpaMemberRepository(em);
    }

    @Test
    void save() {
        // given
        Member member = new Member("memberA");

        // when
        repository.save(member);

        // then
        Optional<Member> findMember = repository.findById(member.getId());
        assertTrue(findMember.isPresent());
    }
}