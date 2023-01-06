package com.vouchit.backend.controller;

import com.vouchit.backend.model.request.company.CompanyRequest;
import com.vouchit.backend.model.request.company.CompanySignInRequest;
import com.vouchit.backend.model.response.CompanyCouponResponse;
import com.vouchit.backend.model.response.CompanyResponse;
import com.vouchit.backend.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/company")
@CrossOrigin("*")
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @Transactional
    @PostMapping("/login")
    public ResponseEntity<CompanyResponse> login(@RequestBody CompanySignInRequest companySignInRequest) {
        log.info("CompanyController: login: logInRequest: {}", companySignInRequest);

        CompanyResponse companyResponse = companyService.login(companySignInRequest.email(), companySignInRequest.password());
        return ResponseEntity.status(HttpStatus.OK).body(companyResponse);
    }

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(companyRequest));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id)
                .orElseThrow(() -> new RuntimeException("Company not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }

}
