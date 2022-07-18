package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c.children from Company c")
    List<Company> findAllChildCompany(Long id);
}
