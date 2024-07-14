package com.enigma.challengeshop.service.impl;

import com.enigma.challengeshop.entity.TransactionBillDetail;
import com.enigma.challengeshop.repository.TransactionBillDetailRepository;
import com.enigma.challengeshop.service.TransactionBillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionBillDetailServiceImpl implements TransactionBillDetailService {
    private final TransactionBillDetailRepository transactionBillDetailRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TransactionBillDetail> createBulk(List<TransactionBillDetail> transactionDetails) {
        return transactionBillDetailRepository.saveAllAndFlush(transactionDetails);
    }
}
