package com.vouchit.backend.repository;

import com.vouchit.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from Company c where c.user.email = :email")
    Optional<Company> findCompanyByEmail(String email);
}
