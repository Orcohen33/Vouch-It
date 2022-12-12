package com.vouchit.backend.model.request;

import com.vouchit.backend.model.request.customer.CustomerRequest;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequest {

    @Min(1)
    private Double totalPrice;
    @NotNull(message = "Date is required")
    private LocalDateTime date;
    @NotNull(message = "Customer is required")
    private CustomerRequest customer;
    private Set<CouponRequest> coupons;
}
