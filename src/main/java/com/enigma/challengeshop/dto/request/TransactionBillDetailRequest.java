package com.enigma.challengeshop.dto.request;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionBillDetailRequest {
    private String menuId;
    private Integer qty;
}
