package me.kenux.playground.jpa;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class EntityManagerTest {

    @PersistenceUnit
    EntityManagerFactory emf;

    private EntityManager em;
    private EntityTransaction tx;

    @BeforeEach
    void beforeEach() {
        em = emf.createEntityManager();
        tx =em.getTransaction();
    }

    @AfterEach
    void afterEach() {
        em.close();
        emf.close();
    }

    @Test
    void idStrategyAutoIdentity() {
        Item item = new Item("itemA", 1000, 10);
        saveItemNoFlush(item);

        log.info("find item...");
        Item findItem = em.find(Item.class, item.getId());
        assertThat(findItem.getId()).isEqualTo(item.getId());
        log.info("findItem={}", findItem);
    }

    @Test
    void idStrategyManual() {
        Item item = new Item(1L, "itemA", 1000, 10);
        saveItemNoFlush(item);

        log.info("find item...");
        Item findItem = em.find(Item.class, item.getId());
        assertThat(findItem.getId()).isEqualTo(item.getId());
        log.info("findItem={}", findItem);
    }

    @Test
    void findAll() {
        // given
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 1000, 10);
        saveItemAndFlush(itemA, itemB);
        log.info("item 저장 완료");

        // when
        log.info("find Item...");
        String query = "select i from Item i";
        List<Item> resultList = em.createQuery(query, Item.class).getResultList();

        // then
        assertThat(resultList).hasSize(2);
    }



    private void saveItemNoFlush(Item ...items) {
        log.info("tx begin....");
        tx.begin();
        try {
            log.info("persist....");
            for (Item item : items) {
                em.persist(item);
            }
            log.info("tx commit");
            tx.commit();
        } catch (Exception e) {
            log.info("tx rollback");
            tx.rollback();
        }
    }

    private void saveItemAndFlush(Item ...items) {
        log.info("tx begin....");
        tx.begin();
        try {
            log.info("persist....");
            for (Item item : items) {
                em.persist(item);
            }
            log.info("flush.....");
            em.flush();
            log.info("clear.....");
            em.clear();
            log.info("tx commit");
            tx.commit();
        } catch (Exception e) {
            log.info("tx rollback");
            tx.rollback();
        }
    }
}
