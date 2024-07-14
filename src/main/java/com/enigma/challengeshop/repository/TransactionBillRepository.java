package com.enigma.challengeshop.repository;

import com.enigma.challengeshop.entity.TransactionBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionBillRepository extends JpaRepository<TransactionBill, String> {
}
