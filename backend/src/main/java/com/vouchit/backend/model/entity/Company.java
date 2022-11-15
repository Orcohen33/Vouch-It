package com.vouchit.backend.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"coupons"})
@EqualsAndHashCode(of = {"id"})
@Builder
@Table(name = "companies")

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private Set<Coupon> coupons;

//    public void setCoupons(Set<Coupon> coupons) {
//        coupons.forEach(coupon -> coupon.setCompany(this));
//        this.coupons = coupons;
//    }
}

