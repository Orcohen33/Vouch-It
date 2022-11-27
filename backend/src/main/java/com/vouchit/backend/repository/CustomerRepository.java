package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsCustomerByEmailAndPassword(String email, String password);
    boolean existsCustomerByEmail(String email);

    Customer findCustomerByEmailAndPassword(String email, String password);

}
