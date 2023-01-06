package com.vouchit.backend.repository;

import com.vouchit.backend.model.entity.Customer;
import com.vouchit.backend.model.response.CustomerSignInResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    boolean existsCustomerByEmailAndPassword(String email, String password);
//    boolean existsCustomerByEmail(String email);
    @Query("select c from Customer c where c.user.email = ?1 and c.user.password = ?2")
    Customer findCustomerByEmailAndPassword(String email, String password);

}
