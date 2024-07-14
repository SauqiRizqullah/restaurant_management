package com.enigma.challengeshop.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionBillDetailResponse {
    private String trxDetailId;
    private String menuId;
    private Long menuPrice;
    private Integer qty;
}
