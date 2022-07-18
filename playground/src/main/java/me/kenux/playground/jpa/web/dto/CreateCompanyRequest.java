package me.kenux.playground.jpa.web.dto;

import lombok.Data;
import me.kenux.playground.jpa.domain.Company;
import me.kenux.playground.jpa.domain.CompanyType;

@Data
public class CreateCompanyRequest {

    private String name;
    private CompanyType type;

    private Long parentCompanyId;
    private Long memberId;

    public Company toEntity() {
        return new Company(name, type, memberId);
    }
}
