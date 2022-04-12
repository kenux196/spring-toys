package me.kenux.jpalearn.persistcontext;

import lombok.extern.slf4j.Slf4j;
import me.kenux.jpalearn.config.QuerydslConfig;
import me.kenux.jpalearn.domain.member.domain.Book;
import me.kenux.jpalearn.domain.member.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
public class InheritTest {

    @Autowired
    private EntityManagerFactory emf;

    private EntityManager em;

    @BeforeEach
    void init() {
        em = emf.createEntityManager();
    }

    @Test
    void saveBookItem() {
        Item item = new Book("1", "book 1", 10000, "김작가", "123123");

        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();

    }
}