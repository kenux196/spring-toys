package me.kenux.playground.jpa.service;

import lombok.RequiredArgsConstructor;
import me.kenux.playground.jpa.domain.Company;
import me.kenux.playground.jpa.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public Long createCompany(Company company, Long parentCompanyId) {
        if (parentCompanyId != null) {
            final Company parentCompany = getCompany(parentCompanyId);
            company.changeParentCompany(parentCompany);
        }
        return companyRepository.save(company).getId();
    }

    @Transactional
    public void changeParentCompany(Long childId, Long parentId) {
        final Company childCompany = getCompany(childId);
        final Company parentCompany = getCompany(parentId);
        childCompany.changeParentCompany(parentCompany);
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("검색된 회사가 없습니다."));
    }

    public List<Company> getChildCompany(Long id) {
        return companyRepository.findAllChildCompany(id);
    }
}
