package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Address;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("거주 도시가 대구인 회원 검색")
    void findByCity() {
        // given
        prepareTestData();

        // when
        List<Member> findMembers = memberRepository.findAllByCity("대구");

        // then
        assertThat(findMembers).hasSize(50);
    }

    @Test
    @DisplayName("회원 이름으로 검색")
    void findByName() {
        // given
        prepareTestData();

        // when
        List<Member> members = memberRepository.findAllByName("회원1");

        // then
        assertThat(members).hasSize(1);
    }

    @Test
    @DisplayName("Repository 는 AOP 동적 프록시 객체이다.")
    void dynamicProxy() {
        System.out.println("memberRepository = " + memberRepository.getClass());
        assertThat(AopUtils.isAopProxy(memberRepository)).isTrue();
    }

    @Test
    @DisplayName("name 속성 uniq 테스트")
    void isUniqNameColumn() {
        prepareTestData();

        Member member = new Member("회원1", new Address());
        assertThatThrownBy(() -> memberRepository.save(member))
                .isInstanceOf(Exception.class);
    }

    private void prepareTestData() {

        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String city = i % 2 == 0 ? "대구" : "서울";
            Address address = new Address(city, "도로", "1");
            Member member = new Member("회원" + i, address);
            members.add(member);
        }
        memberRepository.saveAllAndFlush(members);
    }
}
