package me.kenux.playground.jpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", nullable = false)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "company_id")
    private Company parentCompany;

    @OneToMany(mappedBy = "parentCompany")
    private List<Company> children = new ArrayList<>();

    private Long createdId;

    private LocalDateTime createdDate;

    private Long updatedId;

    private LocalDateTime updatedDate;

    public Company(String name, CompanyType type, Long createdId) {
        this.name = name;
        this.type = type;
        this.createdId = createdId;
        this.createdDate = LocalDateTime.now();
    }

    public void changeParentCompany(Company company) {
        if (this.parentCompany != null) {
            this.parentCompany.getChildren().remove(this);
        }
        this.parentCompany = company;
        company.getChildren().add(this);
    }

}
