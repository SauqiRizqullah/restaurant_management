package com.enigma.challengeshop.service.impl;

import com.enigma.challengeshop.dto.request.TransactionBillRequest;
import com.enigma.challengeshop.dto.response.TransactionBillDetailResponse;
import com.enigma.challengeshop.dto.response.TransactionBillResponse;
import com.enigma.challengeshop.entity.*;
import com.enigma.challengeshop.repository.TransactionBillRepository;
import com.enigma.challengeshop.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionBillServiceImpl implements TransactionBillService {
    private final TransactionBillRepository transactionBillRepository;

    private final TransactionBillDetailService transactionBillDetailService;
    private final MenuService menuService;
    private final TableService tableService;
    private final CustomerService customerService;
    private final TransTypeService transTypeService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionBillResponse createNewTransaction(TransactionBillRequest transactionRequest) {
        // 1. Perhatikan entity dari TransactionBill, maka kita harus mendapatkan objek dari customer, tabel, dan transtype terlebih dahulu dengan Idnya

        Customer customer = customerService.getCustomerByIdV2(transactionRequest.getCustomerId());

        Table table = tableService.getTableByIdV2(transactionRequest.getTableId());

        TransType transType = transTypeService.getTransTypeById(transactionRequest.getTransTypeId());

        // 2. Pembuatan Objek Transaksi

        TransactionBill trans = TransactionBill.builder()
                .customer(customer)
                .table(table)
                .transType(transType)
                .transDate(new Date())
                .build();

        // 3. Simpan transaksi terlebih dahulu
        TransactionBill savedTransaction = transactionBillRepository.save(trans);



        // 4. Mengurus detail dari transaksi tersebut
        List<TransactionBillDetail> trxDetail = transactionRequest.getTransactionDetails().stream()
                .map(detailRequest -> {
                    // 1. Informasi dari quantity request harus dikumpulkan melalui stream dan map

                    log.info("Quantity dari detail request: {}", detailRequest.getQty());

                    // 2. Memanggil objek menu yang dilampirkan pada transaksi detail

                    Menu menu = menuService.getMenuByIdV2(detailRequest.getMenuId());

                    return TransactionBillDetail.builder()
                            .trx(savedTransaction)
                            .menu(menu)
                            .menuPrice(menu.getMenuPrice())
                            .qty(detailRequest.getQty())
                            .build();
                }).toList();

        // 3. Membuat transaksi detail dari servicenya langsung

        transactionBillDetailService.createBulk(trxDetail);
        savedTransaction.setTransactionBillDetails(trxDetail);

        // 4. Membuat transaksi response

        List<TransactionBillDetailResponse> trxDetailResponse = trxDetail.stream().map(
                detail -> {
                    return TransactionBillDetailResponse.builder()
                            .trxDetailId(detail.getTrxDetailId())
                            .menuId(detail.getMenu().getMenuId())
                            .menuPrice(detail.getMenu().getMenuPrice())
                            .qty(detail.getQty())
                            .build();
                }).toList();

        return TransactionBillResponse.builder()
                .trxId(savedTransaction.getTrxId())
                .customerId(savedTransaction.getCustomer().getCustomerId())
                .tableId(savedTransaction.getTable().getTableId())
                .transTypeId(savedTransaction.getTransType().getTransTypeId())
                .transDate(savedTransaction.getTransDate())
                .transactionDetails(trxDetailResponse)
                .build();

    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionBillResponse> getAllTransactions() {
        // 1. Pembuatan objek transaksi
        List<TransactionBill> transaction = transactionBillRepository.findAll();

        return transaction.stream().map(trx -> {
            List<TransactionBillDetailResponse> trxDetailResponse = trx.getTransactionBillDetails().stream().map(detail -> {
                return TransactionBillDetailResponse.builder()
                        .trxDetailId(detail.getTrxDetailId())
                        .menuId(detail.getMenu().getMenuId())
                        .menuPrice(detail.getMenu().getMenuPrice())
                        .qty(detail.getQty())
                        .build();
            }).toList();
            return TransactionBillResponse.builder()
                    .trxId(trx.getTrxId())
                    .customerId(trx.getCustomer().getCustomerId())
                    .tableId(trx.getTable().getTableId())
                    .transTypeId(trx.getTransType().getTransTypeId())
                    .transDate(trx.getTransDate())
                    .transactionDetails(trxDetailResponse)
                    .build();

        }).toList();
    }

    // PR =>>> coba koreksi service impl ini kenapa ID transaksi dan detail bisa null


}
