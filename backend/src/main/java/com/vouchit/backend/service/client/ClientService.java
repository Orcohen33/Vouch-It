package com.vouchit.backend.service.client;


import com.vouchit.backend.repository.CompanyRepository;
import com.vouchit.backend.repository.CouponRepository;
import com.vouchit.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

    private final CompanyRepository companyRepository;

    private final CustomerRepository customerRepository;

    private final CouponRepository couponRepository;


    @Autowired
    public ClientService(CompanyRepository companyRepository,
                            CustomerRepository customerRepository,
                            CouponRepository couponRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

    public abstract boolean login(String email, String password);

}
