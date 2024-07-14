package com.enigma.challengeshop.repository;

import com.enigma.challengeshop.entity.TransactionBillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionBillDetailRepository extends JpaRepository<TransactionBillDetail, String> {
}
