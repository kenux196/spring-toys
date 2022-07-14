package me.kenux.playground.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Project;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
class ProjectRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    @DisplayName("jpql 사용한 조회")
    void find1() {
        // given
        final Project project = new Project("project1");
        projectRepository.save(project);

        // when
        final List<Project> result = projectRepository.findAllProject();

        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    @DisplayName("jpql 사용한 조회")
    void find2() {
        // given
        final Project project = new Project("project1");
        projectRepository.save(project);

        // when
        final List<Project> result = projectRepository.findAll();

        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    @DisplayName("dirty checking update")
    void update1() {
        // given
        final Project project = new Project("project1");
        projectRepository.save(project);
        project.changeName("test");

        em.flush();
    }

    @Test
    @DisplayName("jpql 사용한 갱신 -> @Modifying 사용해야 한다.")
    void update2() {
        // given
        final Project project = new Project("project1");
        projectRepository.save(project);
        em.flush();

        String name = "test";

        // when

        // @Modifying 미사용으로 에러 발생 => JPQL로 벌크 업데이트 시 필수
        assertThatThrownBy(() -> projectRepository.update1(name, project.getId()))
            .isInstanceOf(InvalidDataAccessApiUsageException.class);

        // @Modifying 했지만, 영속성 컨텍스트는 기존 그대로 유지됨. 갱신하도록 해야 함.
        log.info("before project name = {}", project.getName());
        projectRepository.update2(name, project.getId());
        Project findProject1 = projectRepository.findById(project.getId()).get();
        log.info("after project name = {}", findProject1.getName());
        assertThat(findProject1.getName()).isNotEqualTo(name);

        // @Modifying(clearAutomatically = true) 영속성 컨텍스트를 clear 하게 됨.
        log.info("before project name = {}", project.getName());
        projectRepository.update3(name, project.getId());
        Project findProject2 = projectRepository.findById(project.getId()).get();
        log.info("after project name = {}", findProject2.getName());
        assertThat(findProject2.getName()).isEqualTo(name);
    }

}
