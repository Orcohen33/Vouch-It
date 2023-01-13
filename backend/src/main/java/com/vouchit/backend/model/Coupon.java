package com.vouchit.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "coupons", indexes = {
        @Index(name = "idx_coupon_category_id", columnList = "category_id"),
        @Index(name = "idx_coupon_company_id", columnList = "company_id")
})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer amount;
    private Double price;
    @Lob @Basic(fetch = FetchType.LAZY)
    private Byte[] image;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    @ToString.Exclude
    private Company company;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "customers_coupons",
            joinColumns = @JoinColumn(name = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    @ToString.Exclude
    private Set<Customer> customers;

    @ManyToMany(mappedBy = "coupons", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Purchase> purchases;

}
