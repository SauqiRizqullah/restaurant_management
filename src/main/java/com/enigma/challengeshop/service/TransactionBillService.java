package com.enigma.challengeshop.service;

import com.enigma.challengeshop.dto.request.TransactionBillRequest;
import com.enigma.challengeshop.dto.response.TransactionBillResponse;
import com.enigma.challengeshop.entity.TransactionBill;

import java.util.List;

public interface TransactionBillService {
//    //1. Insert
//    TransactionBill createNewTransactionBill(TransactionBill transactionBill);
//    //2. Select byTransactionBillId
//    TransactionBill getByTransactionBillId(Long billId);
//    //3. Update
//    TransactionBill update (TransactionBill transactionBill);
//    //4. Delete byTransactionBillId
//    void deleteByTransactionBillId (Long billId);
    TransactionBillResponse createNewTransaction(TransactionBillRequest transactionRequest);

    List<TransactionBillResponse> getAllTransactions();

    //PR lanjut transserviceimpl
}
