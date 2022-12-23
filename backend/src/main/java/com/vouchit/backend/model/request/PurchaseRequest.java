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

    @NotNull(message = "Customer is required")
    private Long customerId;
    private Set<Long> couponIds;
    @Min(1)
    private Double totalPrice;
}
