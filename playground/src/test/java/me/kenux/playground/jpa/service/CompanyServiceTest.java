package me.kenux.playground.jpa.service;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.domain.Company;
import me.kenux.playground.jpa.domain.CompanyType;
import me.kenux.playground.jpa.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CompanyServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;


    @BeforeEach
    void init() {
        companyRepository.deleteAll();
    }


    @Test
    @DisplayName("회사 하나 등록")
    void saveOne() throws Exception {
        // given
        Long parentId = null;
        Company company = new Company("회사1", CompanyType.SUBSIDIARY, 1L);

        // when
        companyService.createCompany(company, parentId);

        // then
        final Optional<Company> find = companyRepository.findById(company.getId());
        assertThat(find).isNotEmpty();
    }

    @Test
    @DisplayName("자회사 변경.")
    @Transactional
    void changeParentCompany() throws Exception {
        // given
        Company parent = new Company("모회사", CompanyType.SUBSIDIARY, 1L);
        companyService.createCompany(parent, null);

        Company child = new Company("자회사", CompanyType.DISTY, 1L);
        companyService.createCompany(child, parent.getId());

        // when
        companyService.changeParentCompany(child.getId(), parent.getId());

        // then
        assertThat(child.getParentCompany()).isEqualTo(parent);
    }


}
