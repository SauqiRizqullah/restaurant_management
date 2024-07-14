package com.enigma.challengeshop.controller;

import com.enigma.challengeshop.constant.APIUrl;
import com.enigma.challengeshop.dto.request.TransactionBillRequest;
import com.enigma.challengeshop.dto.response.TransactionBillResponse;
import com.enigma.challengeshop.service.TransactionBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionBillController {

    private final TransactionBillService transactionBillService;

    @PostMapping(produces = "application/json")
    public TransactionBillResponse createNewTransaction (
            @RequestBody TransactionBillRequest transactionBillRequest
            ){
        return transactionBillService.createNewTransaction(transactionBillRequest);
    }

    @GetMapping(produces = "application/json")
    public List<TransactionBillResponse> getAllTransactions (){
        return transactionBillService.getAllTransactions();
    }

}
