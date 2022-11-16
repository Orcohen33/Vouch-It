package com.vouchit.backend.model.response;

import com.vouchit.backend.model.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Coupon> coupons;
}
