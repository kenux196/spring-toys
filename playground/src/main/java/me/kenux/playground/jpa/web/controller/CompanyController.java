package me.kenux.playground.jpa.web.controller;

import lombok.RequiredArgsConstructor;
import me.kenux.playground.jpa.service.CompanyService;
import me.kenux.playground.jpa.web.dto.ChangeParentCompanyRequest;
import me.kenux.playground.jpa.web.dto.CreateCompanyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody CreateCompanyRequest request) {
        final Long companyId = companyService.createCompany(request.toEntity(), request.getParentCompanyId());
        return ResponseEntity.ok(companyId);
    }

    @PutMapping
    public ResponseEntity<?> changeParentCompany(@RequestBody ChangeParentCompanyRequest request) {
        companyService.changeParentCompany(request.getMyCompanyId(), request.getParentCompanyId());
        return ResponseEntity.ok().build();
    }
}
