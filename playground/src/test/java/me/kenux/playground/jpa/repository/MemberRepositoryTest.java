package me.kenux.playground.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Address;
import me.kenux.playground.jpa.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
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

    @Test
    @DisplayName("paging 테스트")
    void paging() {
        prepareTestData();

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "name"));

        Page<Member> page = memberRepository.findAll(pageRequest);

        System.out.println("page.getTotalPages() = " + page.getTotalPages());
        List<Member> content = page.getContent();
        System.out.println("content= " + content);
    }

    @Test
    @DisplayName("1만 건 save() 시간 테스트")
    void save_10000() {
        final List<Member> members = createMember(10000);
        final long startTime = System.currentTimeMillis();
        for (Member member : members) {
            memberRepository.save(member);
        }
        log.info("1만건 save 시간 = {}", System.currentTimeMillis() - startTime);
    }

    @Test
    @DisplayName("1만 건에 대한 saveAll 시간 테스트")
    void saveAll_10000() throws Exception {
        // given
        final List<Member> members = createMember(10000);
        final long startTime = System.currentTimeMillis();

        // when
        memberRepository.saveAll(members);

        // then
        log.info("1만건 saveAll() 시간 = {}", System.currentTimeMillis() - startTime);
    }

    private List<Member> createMember(int size) {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            members.add(new Member("member" + i));
        }
        return members;
    }

    private void prepareTestData() {

        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String city = i % 2 == 0 ? "대구" : "서울";
            Address address = new Address(city, "도로", "1");
            Member member = new Member("회원" + i, address);
            members.add(member);
        }
        memberRepository.saveAllAndFlush(members);;
    }
}
