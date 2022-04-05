package me.kenux.jpalearn.domain.member.repository;

import me.kenux.jpalearn.domain.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@DataJpaTest
class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MemberRepository memberRepository;

    private final List<Member> members = new ArrayList<>();

    @BeforeEach
    void setup() {
        createMembers(100);
    }

    @Test
    void jdbcInsertTest() {
        System.out.println("jdbcTemplate = " + jdbcTemplate);
        insertUsingJdbcTemplate(members, 1000);
    }

    @Test
    void jpaInsertTest() {
        insertUsingJpa(members);
    }

    private List<Member> createMembers(int number) {
        for (int i = 0; i < number; i++) {
            Member member = Member.builder()
                    .name("member" + i)
                    .address("대구시")
                    .phone("010-2323-9090")
                    .age(40)
                    .build();
            members.add(member);
        }
        return members;
    }

    private void insertUsingJdbcTemplate(List<Member> members, int batchSize) {
        String query = "insert into member(name, address, age, phone) values (?, ?, ?, ?)";

        final long startTime = System.currentTimeMillis();
        jdbcTemplate.batchUpdate(query, members, batchSize, (ps, argument) -> {
            ps.setString(1, argument.getName());
            ps.setString(2, argument.getAddress());
            ps.setInt(3, argument.getAge());
            ps.setString(4, argument.getPhone());
        });

        final long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("insert batch using JDBCTemplate resultTime = " + resultTime + " ms");

        final List<Map<String, Object>> foundMember = jdbcTemplate.queryForList("select * from member where id = 1000");
        System.out.println("foundMember = " + foundMember);
    }

    private void insertUsingJpa(List<Member> members) {
        final long startTime = System.currentTimeMillis();
        memberRepository.saveAll(members);
        final long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("insert batch using JPA resultTime = " + resultTime + " ms");
    }
}