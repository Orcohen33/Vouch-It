package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company c where c.user.email = :email")
    Company findCompanyByEmail(String email);
}
