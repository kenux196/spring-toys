package me.kenux.playground.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
class EntityManagerTest {

    @Autowired
    EntityManager em;

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 1000, 10);

        // when
        em.persist(item);
        em.clear();

        // then
        Item findItem = em.find(Item.class, item.getId());
        assertThat(findItem.getId()).isEqualTo(item.getId());
        System.out.println("findItem = " + findItem);
    }

    @Test
    void findAll() {
        // given
        Item itemA = new Item("itemA", 1000, 10);
        em.persist(itemA);
        log.info("itemA 저장");
        Item itemB = new Item("itemB", 1000, 10);

        em.persist(itemB);
        em.clear();

        // when
        String query = "select i from Item i";
        List<Item> resultList = em.createQuery(query, Item.class).getResultList();

        // then
        assertThat(resultList).hasSize(2);
    }
}
