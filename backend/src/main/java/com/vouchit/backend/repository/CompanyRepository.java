package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsCompanyByEmailAndPassword(String email, String password);
    boolean existsCompanyByEmail(String email);

    Company findCompanyByEmailAndPassword(String email, String password);
}
