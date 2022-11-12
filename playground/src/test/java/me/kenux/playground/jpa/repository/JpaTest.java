package me.kenux.playground.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.domain.Project;
import me.kenux.playground.jpa.service.JpaMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Import(QuerydslConfig.class)
@Transactional
class JpaTest {

    @Autowired
    EntityManager em;
    @Autowired
    JpaMemberRepository jpaMemberRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    JpaMemberService memberService;

    @Test
    @DisplayName("동일한 영속성 컨텍스트의 엔티티 비교 시, 동일성(==) 비교 성공한다.")
    @Transactional
    void compareEntity1() {
        // given
        Member member = new Member("member1");

        // when
        Long saveId = memberService.join(member);
        log.info("======= service join end ======");

        // then
        // Participating in existing transaction => 트랜잭션 전파.
        Member findMember = jpaMemberRepository.findOne(saveId);
        log.info("======= repository find one end ======");
        // findMember 는 준영속 상태

        // 둘은 다른 주소값을 가진 인스턴스다.
        assertThat(member).isSameAs(findMember);
    }

    @Test
    @DisplayName("다른 영속성 컨텍스트의 엔티티 비교 시, 동일성(==) 비교는 실패한다. 동등성 비교를 해야 한다.")
    void compareEntity2() {
        // given
        Member member = new Member("member1");

        // 아래 둘은 서로 다른 트랜잭션으로 수행된다.
        // when
        Long saveId = memberService.join(member);
        log.info("======= service join end ======");

        // then
        Member findMember = jpaMemberRepository.findOne(saveId);
        log.info("======= repository find one end ======");
        // findMember 는 준영속 상태

        // 둘은 다른 주소값을 가진 인스턴스다.
        assertThat(member).isNotSameAs(findMember);
    }

    @Test
    @DisplayName("영속성 컨텍스트와 프록시1 - 프록시 조회 후 원본 엔티티 조회 시 동일성 보장")
    @Transactional
    void persistenceContextAndProxy1() {
        Member newMember = new Member("member1");
        em.persist(newMember);
        em.flush();
        em.clear();

        Member refMember = em.getReference(Member.class, newMember.getId());
        Member findMember = em.find(Member.class, newMember.getId());

        log.info("refMember Type = {}", refMember.getClass());
        log.info("findMember Type = {}", findMember.getClass());

        assertThat(refMember).isSameAs(findMember);
    }

    @Test
    @DisplayName("영속성 컨텍스트와 프록시2 - 원본 엔티티로 조회 후 프록시 조회 시 동일성 보장")
    @Transactional
    void persistenceContextAndProxy2() {
        Member newMember = new Member("member1");
        em.persist(newMember);
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, newMember.getId());
        Member refMember = em.getReference(Member.class, newMember.getId());

        log.info("refMember Type = {}", refMember.getClass());
        log.info("findMember Type = {}", findMember.getClass());

        assertThat(refMember).isSameAs(findMember);
    }

    @Test
    @DisplayName("프록시 타입 비교 예제")
    @Transactional
    void compareProxyType() {
        Member newMember = new Member("member1");
        em.persist(newMember);
        em.flush();
        em.clear();

        Member refMember = em.getReference(Member.class, newMember.getId());
        log.info("refMember Type = {}", refMember.getClass());

        assertThat(Member.class).isNotSameAs(refMember.getClass());
        assertThat(refMember).isInstanceOf(Member.class);
    }

    @Test
    @DisplayName("프록시 동등성 비교")
    @Transactional
    void proxyEquals() {
        Member saveMember = new Member("member1");
        em.persist(saveMember);
        em.flush();
        em.clear();

        Member newMember = new Member("member1");
        Member refMember = em.getReference(Member.class, saveMember.getId());

        assertThat(newMember.equals(refMember)).isTrue();
    }

    @Test
    @DisplayName("등록 배치 - 끊어가기")
    @Transactional
    void batchInsert() {
        for (int i = 0; i < 10000; i++) {
            final Member member = new Member("member" + i);
            log.info("영속화 1 ~ {}", i);
            em.persist(member);
            log.info("영속화 2 ~ {}", i);

            if (i % 100 == 0) {
                log.info("100건씩 DB로 반영하고, 영속성 컨텍스트 초기화 현재 건수 = {}", i);
                em.flush();
                em.clear();
            }
        }
    }

    @Test
    @DisplayName("save vs saveAll - save time")
    void save() {
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            final Member member = new Member("member" + i);
            memberRepository.save(member);
        }
        log.info("save 소요 시간 = {}", System.currentTimeMillis() - startTime);
    }

    @Test
    @DisplayName("save vs saveAll - save time")
    @Transactional
    void save2() {
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            final Member member = new Member("member" + i);
            memberRepository.save(member);
        }
        log.info("save 소요 시간 = {}", System.currentTimeMillis() - startTime);
    }

    @Test
    @DisplayName("save vs saveAll - saveAll Time")
    void saveAll() {
        final long startTime = System.currentTimeMillis();
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            final Member member = new Member("member" + i);
            members.add(member);
        }
        memberRepository.saveAll(members);
        log.info("save 소요 시간 = {}", System.currentTimeMillis() - startTime);
    }

    @Test
    @DisplayName("save vs saveAll - saveAll Time")
    @Transactional
    void saveAll2() {
        final long startTime = System.currentTimeMillis();
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            final Member member = new Member("member" + i);
            members.add(member);
        }
        memberRepository.saveAll(members);
        log.info("save 소요 시간 = {}", System.currentTimeMillis() - startTime);
    }


    @Test
    @DisplayName("수정 배치 처리 - 페이징 이용")
    @Transactional
    void batchUpdate() {
        // given
        for (int i = 0; i < 10000; i++) {
            final Member member = new Member("member" + i);
            em.persist(member);
        }
        em.flush();
        em.clear();

        // when
        int pageSize = 100;
        for (int i = 0; i < 10; i++) {
            final List<Member> resultList = em.createQuery("select m from Member m", Member.class)
                .setFirstResult(i * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

            // 비즈니스 로직 실행
            for (Member member : resultList) {
                member.changeAge(10);
            }
            em.flush();
            em.clear();
        }
    }

    @Test
    @DisplayName("쓰기 지연 테스트1 - Id 생성 전략이 identity 아닌 경우만 해당된다.")
    @Transactional
    void lazyWrite() {
        for (int i = 0; i < 100; i++) {
            final Project project = new Project("project" + i);
            log.info("등록 ");
            em.persist(project);
        }
        log.info("============== flush =====================");
        em.flush();
        //em.clear();

        final List<Project> projectList = em.createQuery("select p from Project p", Project.class)
            .getResultList();

        log.info(" ============ 이름 변경 ====================");
        for (Project project : projectList) {
            log.info("이름 변경 중");
            project.changeName("ppp");
        }
        log.info(" ============= 이름 변경 완료 ====================");
        em.flush();
    }
}
