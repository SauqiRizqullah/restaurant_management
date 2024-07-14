package com.enigma.challengeshop.dto.request;

import com.enigma.challengeshop.entity.TransactionBillDetail;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionBillRequest {
    private String customerId;
    private String tableId;
    private String transTypeId;
    private List<TransactionBillDetailRequest> transactionDetails;
    private Date transDate;
}
