package com.enigma.challengeshop.service;

import com.enigma.challengeshop.entity.TransactionBill;
import com.enigma.challengeshop.entity.TransactionBillDetail;

import java.util.List;

public interface TransactionBillDetailService {
    List<TransactionBillDetail> createBulk (List<TransactionBillDetail> transactionDetails);
}
