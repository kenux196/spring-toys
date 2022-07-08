package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Address;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
class JpaBasicTest {

    @Autowired
    EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("연관관계 테스트 - 멤버 조회")
    void test1() {
        Team team = new Team("팀A");
        teamRepository.save(team);

        Address address = new Address("서울", "도로", "1");
        Member member = new Member("회원1", address);
        memberRepository.save(member);
        member.joinTeam(team);

        em.flush();
        em.clear();

        final Optional<Member> findMember = memberRepository.findById(member.getId());
        findMember.ifPresent(member1 -> {
            System.out.println("member1 = " + member1);
            final Team team1 = member1.getTeam();
            System.out.println("team1.getClass() ------ 1 = " + team1.getClass());
            System.out.println("team1.getName() = " + team1.getName());
            System.out.println("team1.getClass() ------ 2 = " + team1.getClass());
        });
    }

    @Test
    @DisplayName("연관관계 테스트 - 팀 조회")
    void test2() {
        Team team = new Team("팀A");
        teamRepository.save(team);

        Address address = new Address("서울", "도로", "1");
        Member member = new Member("회원1", address);
        memberRepository.save(member);
        member.joinTeam(team);

        em.flush();
        em.clear();

        final Optional<Team> findTeam = teamRepository.findById(team.getId());
        findTeam.ifPresent(team1 -> {
            System.out.println("team1 = " + team1.getClass());
            final List<Member> members1 = team1.getMembers();
//            Set<Member> members1 = team1.getMembers();
            for (Member member1 : members1) {
                System.out.println("member1 = " + member1.getClass());
            }
        });
        
        em.clear();

        final List<Member> allByTeamId = memberRepository.findAllByTeamId(team.getId());
        allByTeamId.forEach(member1 -> {
            System.out.println("member1.getClass() = " + member1.getClass());
        });
    }

    @Test
    @DisplayName("연관관계 테스트 - 팀Id로 Member 조회")
    void test3() {
        Team team = new Team("팀A");
        teamRepository.save(team);

        Address address = new Address("서울", "도로", "1");
        Member member = new Member("회원1", address);
        memberRepository.save(member);
        member.joinTeam(team);

        Member member1 = new Member("회원2", address);
        memberRepository.save(member1);
        member1.joinTeam(team);


        em.flush();
        em.clear();

        final List<Member> allByTeamId = memberRepository.findAllByTeamId(team.getId());
        allByTeamId.forEach(memberX -> {
            System.out.println("member1.getClass() = " + member1.getClass());
        });
    }

    @Test
    @DisplayName("N+1 문제 예시")
    void nPlusOneIssueTest1() {
        prepareNPlusOneIssueData();

        System.out.println("================================================");;
        System.out.println("============= 멤버 조회 => 쿼리 1회");
        final List<Member> members = memberRepository.findAll();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");;
        System.out.println("------------------------------------------------");
        for (Member member : members) {
            System.out.println("팀... = " + member.getTeam().getName());
        }

    }

    @Test
    @DisplayName("N+1 문제 해결")
    void nPlusOneIssue_solved() {
        prepareNPlusOneIssueData();

        System.out.println("================================================");;
        System.out.println("============= 멤버 조회 => 쿼리 1회");
        final List<Member> members = memberRepository.findAllWithFetchJoin();
        System.out.println("------------------------------------------------");
        for (Member member : members) {
            System.out.println("member = " + member.getName());
        }
    }

    @Test
    @DisplayName("N+1 문제 - 중복 이슈")
    void duplication() {
        createMember();

        System.out.println("==================================================");
        List<Team> allTeamWithMembers = teamRepository.findAllTeamWithMembers();
        allTeamWithMembers.forEach(team -> {
            System.out.println("team = " + team.getName());
            team.getMembers().forEach(member -> {
                System.out.println("    member.getName() = " + member.getName());
            });
        });
    }

    @Test
    @DisplayName("N+1 문제 - 중복 이슈 해결 - distinct 사용")
    void duplication_solved() {
        createMember();

        System.out.println("==================================================");
        List<Team> allTeamWithMembers = teamRepository.findAllTeamWithMembersUsingDistinct();
        allTeamWithMembers.forEach(team -> {
            System.out.println("team = " + team.getName());
            team.getMembers().forEach(member -> {
                System.out.println("    member.getName() = " + member.getName());
            });
        });
    }

    private void prepareNPlusOneIssueData() {
        Address address = new Address("서울", "도로", "1");
        for (int i = 0; i < 10; i++) {
            Team team = new Team("팀A-" + i);
            teamRepository.save(team);
            Member member = new Member("회원" + i, address);
            member.joinTeam(team);
            memberRepository.save(member);
        }

        em.flush();
        em.clear();
    }

    private List<Team> createTeam() {
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Team team = new Team("team " + i);
            teams.add(team);
        }
        teamRepository.saveAll(teams);
        return teams;
    }

    private void createMember() {
        List<Team> teams = createTeam();
        List<Member> members = new ArrayList<>();
        Address address = new Address("서울", "도로", "1");
        for (Team team : teams) {
            for (int i = 0; i < 5; i++) {
                Member member = new Member("member " + i, address);
                member.joinTeam(team);
                members.add(member);
            }
        }
        memberRepository.saveAll(members);

        em.flush();
        em.clear();
    }

}