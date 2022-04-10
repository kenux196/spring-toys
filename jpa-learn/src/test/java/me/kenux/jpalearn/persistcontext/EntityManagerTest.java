package me.kenux.jpalearn.persistcontext;

import me.kenux.jpalearn.config.QuerydslConfig;
import me.kenux.jpalearn.domain.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class EntityManagerTest {

    @Autowired
    EntityManagerFactory emf;

    private EntityManager em;

    @BeforeEach
    void setup() {
        em = emf.createEntityManager();
    }

    @Test
    void memberSaveTest() {
        // given
        Member member = Member.builder()
                .name("memberA")
                .age(30)
                .build();

        // when
        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();
        System.out.println("member = " + member);

        // then
        Member findMember = em.find(Member.class, member.getId());
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    /**
     * PersistenceContext (영속성 컨텍스트) : 엔티티 저장소 / 논리적인 개념
     * EntityManager를 통해서 영속성 컨텍스트에 접근
     * EntityManager:PersistenceContext = N:1
     * Entity Life cycle
     * - 비영속(new / transient) : 영속성 컨텍스트와 전혀 관계없는 새로운 상태
     * - 영속(managed) : 영속성 컨텍스트에 저장되어 관리되는 상태 -> persist()
     * - 준영속(detached) : 영속성 컨텍스트에 저장되었다가 분리된 상태 -> detach(), clear(), close()
     * - 삭제(removed) : 삭제된 상태 -> removed()
     * - flush()
     * - find()
     * - merge()
     * 영속성 컨텍스트의 이점
     * 1. 1차 캐시
     * 2. 동일성(identity)보장
     * 3. 트랙잭션을 지원하는 쓰기 지연(transactional write-behind)
     * 4. 변경 감지(dirty checking)
     * 5. 지연 로딩(lazy loading)
     */
    @Test
    void entityLifeCycleTest() {
        // 객체를 생성한 상태(비영속)
        Member member = Member.builder().name("memberA").build();

        em.getTransaction().begin();
        // 객체를 저장한 상태(영속)
        em.persist(member);

        // 회원 엔티티를 영속성 컨텍스트에서 분리(준영속)
        em.detach(member);

        // 영속 상태로 다시 merge
        em.merge(member);

        // 객체를 삭제한 상태(삭제)
        em.remove(member);
        em.getTransaction().commit();

        System.out.println("member = " + member);
    }

    @Test
    @DisplayName("영속성 컨텍스트 - 1차 캐시 조회")
    void firstCacheTest() {
        // given
        Member member = Member.builder()
                .name("memberA")
                .age(30)
                .build();

        // when
        em.getTransaction().begin();
        em.persist(member);

        // transaction commit 시점에 insert 쿼리가 날아간다.
        em.getTransaction().commit();

        System.out.println("member = " + member);

        // then
        // 조회는 1차 캐시에서 조회가 되므로 쿼리가 날아가지 않는다.
        Member findMember = em.find(Member.class, member.getId());
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("영속성 컨텍스트 - 1차 캐시에 데이터가 없는 경우")
    void entityNotExistInPersistenceContextTest() {
        // given
        Member member = Member.builder()
                .name("memberA")
                .age(30)
                .build();

        // when
        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();

        em.detach(member);

        // then
        // 영속성 컨텍스트에 엔티티가 없으므로 조회 쿼리가 발생한다.
        Member findMember = em.find(Member.class, member.getId());
        // 조회된 결과는 기존 엔티티와 같지 않다.
        assertThat(findMember).isNotEqualTo(member);
        assertThat(findMember.getId()).isEqualTo(member.getId());
        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);
    }
}
