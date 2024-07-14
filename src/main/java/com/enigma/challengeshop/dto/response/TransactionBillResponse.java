package com.enigma.challengeshop.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionBillResponse {
    private String trxId;
    private String customerId;
    private String tableId;
    private String transTypeId;
    private Date transDate;
    private List<TransactionBillDetailResponse> transactionDetails;
}
