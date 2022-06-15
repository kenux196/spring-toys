package me.kenux.playground.jpa.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@DataJpaTest
class EntityManagerTest {

    @Autowired
    EntityManager em;

    @Test
//    @Transactional
    void save() {
        Item item = new Item("itemA", 1000);
        em.persist(item);

        em.clear();

        Item findItem = em.find(Item.class, item.getId());
        System.out.println("findItem = " + findItem);

    }

    @Entity
    @Table(name = "item")
    @NoArgsConstructor
    @Getter
    @ToString
    static class Item {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private Integer price;

        public Item(String name, Integer price) {
            this.name = name;
            this.price = price;
        }
    }

}
