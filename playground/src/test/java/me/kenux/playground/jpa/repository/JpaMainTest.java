package me.kenux.playground.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

/**
 * JPA 기본 엔티티 메니저 팩토리 & 엔티티 매니저
 */
@Slf4j
@SpringBootTest
class JpaMainTest {

    @PersistenceUnit
    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        em = emf.createEntityManager();
    }

    @AfterEach
    void afterEach() {
        em.close();
    }

    @Test
    void jpaMainTest_basic() {
        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();  // 트랜잭션 시작
            logic();   // 비즈니스 로직 실행
            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e) {
            tx.rollback();// 트랜잭션 롤백
        }
    }

    private void logic() {
        log.info("비즈니스로직 수행");
        Member member = new Member("memberA", 20);
        em.persist(member);
    }
}
