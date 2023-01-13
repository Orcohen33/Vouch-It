package com.vouchit.backend.repository;

import com.vouchit.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    boolean existsCustomerByEmailAndPassword(String email, String password);
//    boolean existsCustomerByEmail(String email);
    @Query("select c from Customer c where c.user.email = :email")
    Optional<Customer> findCustomerByEmail(String email);

}
