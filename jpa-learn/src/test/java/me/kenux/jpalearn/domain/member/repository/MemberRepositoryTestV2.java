package me.kenux.jpalearn.domain.member.repository;

import me.kenux.jpalearn.domain.member.domain.Member;
import me.kenux.jpalearn.domain.member.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTestV2 {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    EntityManager em;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    EntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    void saveV1() {
        // given
        Member member = generateMember();

        // when
        System.out.println("Before save");
        memberRepository.save(member);
        System.out.println("After save");

        // then
        final Optional<Member> find = memberRepository.findById(member.getId());
        assertThat(find).isNotEmpty().contains(member);
    }

    @Test
    void saveV2_영속컨텍스트에서_detach_조회() {
        // given
        Member member = generateMember();

        // when
        System.out.println("Before save");
        memberRepository.save(member);
        System.out.println("After save");

        em.detach(member);
        System.out.println("detach 후 member = " + member);

        // then
        final Optional<Member> find = memberRepository.findById(member.getId());
        assertThat(find).isNotEmpty();
        assertThat(find.get()).isNotEqualTo(member);
        System.out.println("find 후 member = " + find.get());
    }

    @Test
    void saveWithTeamV1() {
        final Member member = generateMember();
        final Team team = generateTeam();

        memberRepository.save(member); // member: 영속 상태
        teamRepository.save(team); // team: 영속 상태
        member.changeTeam(team); // member에 team 을 할당하지만, update 쿼리가 나가지 않은 상태

        em.clear(); // 영속성 컨텍스트 비우기

        final Member findMember = memberRepository.findById(member.getId()).get(); // 멤버를 조회

        // 여기서 팀은 데이터가 없다.
        // 멤버에게 팀을 할당한 것을 DB에 반영하기 전에 영속컨텍스트를 비워버렸기 때문이다.
        // 실행되는 쿼리를 보면, member insert, team insert 까지만 날아간다.
        // update 쿼리는 안나간다.
        final Team team1 = findMember.getTeam();
        System.out.println("team1 = " + team1);
    }

    @Test
    void saveWithTeamV2() {
        final Member member = generateMember();
        final Team team = generateTeam();

        memberRepository.save(member); // member: 영속 상태
        teamRepository.save(team); // team: 영속 상태
        member.changeTeam(team); // member 에 team 을 할당하지만, update 쿼리가 나가지 않은 상태

        em.flush(); // DB에 변경 사항 반영한다.
        em.clear(); // 영속성 컨텍스트 비우기

        final Member findMember = memberRepository.findById(member.getId()).get(); // 멤버를 조회

        System.out.println("findMember = " + findMember);
        System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass()); // 이때 team 객체는 proxy 객체이다.
        System.out.println("팀 조회 전");
        // 실행되는 쿼리를 보면, member insert, team insert, update 날아간다.
        // Member와 Team은 관계를 맺을 때, fetch type을 lazy로 설정했으므로 객체 조회하는 시점에 team 조회 쿼리 나간다.
        final Team team1 = findMember.getTeam();
        System.out.println("team1 = " + team1);

    }

    private Member generateMember() {
        return Member.builder()
                .name("member1")
                .build();
    }

    private Team generateTeam() {
        return Team.builder()
                .name("teamA")
                .build();
    }
}