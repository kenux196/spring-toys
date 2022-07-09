package me.kenux.playground.jpa.repository;

import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class BasicJpaTest {

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("회원 등록 테스트")
    void saveMember() {
        Address address = new Address("대구", "대실역남로", "333");
        final Member member = new Member("회원1", address);
        em.persist(member);
        em.flush();
        em.clear();

        final Member findMember = em.find(Member.class, member.getId());

        assertThat(findMember).isNotNull();
    }

    @Test
    @DisplayName("상품 등록 테스트")
    void saveOrder() {
        final Item album = new Album("앨범1", 1000, 10, "김작가");
        em.persist(album);
        em.flush();
        em.clear();

        final Item findItem = em.find(Item.class, album.getId());
        assertThat(findItem).isNotNull();

    }


    private Member getTestMember() {
        Address address = new Address("대구", "대실역남로", "333");
        final Member member = new Member("회원1", address);
        em.persist(member);
        return member;
    }
}
