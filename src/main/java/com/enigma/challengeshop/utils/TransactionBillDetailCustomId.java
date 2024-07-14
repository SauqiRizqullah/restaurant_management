package com.enigma.challengeshop.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TransactionBillDetailCustomId implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        String prefix = "DETAIL";
        String query = "SELECT COALESCE(MAX(CAST(SUBSTRING(trx_detail_id, 7) AS INTEGER)), 0) FROM t_bill_detail";
        Integer max = (Integer) sharedSessionContractImplementor.createNativeQuery(query).getSingleResult();
        int nextId = (max == null ? 1 : max + 1);
        return prefix + String.format("%03d", nextId);
    }
}
