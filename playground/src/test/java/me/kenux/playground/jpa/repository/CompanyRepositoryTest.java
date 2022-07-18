package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.Company;
import me.kenux.playground.jpa.domain.CompanyType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CompanyRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    CompanyRepository companyRepository;

    @Test
    void save() {
        final Company company = new Company("회사", CompanyType.SUBSIDIARY, 1L);
        final Company savedCompany = companyRepository.save(company);

        em.flush();
        em.clear();

        final Optional<Company> findCompany = companyRepository.findById(savedCompany.getId());
        assertThat(findCompany).isNotEmpty();
    }

    @Test
    @DisplayName("하위 회사 - 상위 회사 연결")
    void parentChildTest() {
        Company parent = new Company("모회사", CompanyType.DISTY,1L);
        companyRepository.save(parent);

        Company child = new Company("자회사", CompanyType.INSTALLER, 2L);
        child.changeParentCompany(parent);
        companyRepository.save(child);

        assertThat(child.getParentCompany()).isEqualTo(parent);

    }

    @Test
    @DisplayName("모회사의 모든 자회사 조회")
    void getAllChildCompany() {
        // given
        Company parent = new Company("모회사", CompanyType.DISTY,1L);
        companyRepository.save(parent);

        Company child1 = new Company("자회사1", CompanyType.INSTALLER, 2L);
        child1.changeParentCompany(parent);
        companyRepository.save(child1);
        Company child2 = new Company("자회사2", CompanyType.INSTALLER, 3L);
        child2.changeParentCompany(parent);
        companyRepository.save(child2);

        em.flush();
        em.clear();

        final List<Company> result = companyRepository.findAllChildCompany(parent.getId());
        assertThat(result).hasSize(2);
    }
}
