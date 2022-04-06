package me.kenux.jpalearn.domain.member.repository;

import me.kenux.jpalearn.domain.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private static final int ITEM_COUNT = 100000;

    @BeforeEach
    void setup() {
        createMembers(ITEM_COUNT);
    }

    @Test
    void jdbcInsertTest() {
        System.out.println("jdbcTemplate = " + jdbcTemplate);
        insertUsingJdbcTemplate(members);
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

    private void insertUsingJdbcTemplate(List<Member> members) {
        String query = "insert into member(name, address, age, phone) values (?, ?, ?, ?)";

        final long startTime = System.currentTimeMillis();
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, members.get(i).getName());
                ps.setString(2, members.get(i).getAddress());
                ps.setInt(3, members.get(i).getAge());
                ps.setString(4, members.get(i).getPhone());
            }

            @Override
            public int getBatchSize() {
                return members.size();
            }
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