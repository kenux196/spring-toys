package me.kenux.playground.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Item;
import me.kenux.playground.jpa.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
class EntityManagerForStudentEntityTest {

    @Autowired
    EntityManager em;

    @Test
    void save() {
        // given
        Student student = new Student("student1", "studentA");

        // when
        log.info("student 저장");
        em.persist(student);
        log.info("persist 완료");
        em.flush();
//        em.clear();

        // then
        Student findStudent = em.find(Student.class, student.getId());
        assertThat(findStudent.getId()).isEqualTo(student.getId());
        log.info("findStudent={}", findStudent);
    }
}
