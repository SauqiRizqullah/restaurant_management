package com.enigma.challengeshop.entity;

import com.enigma.challengeshop.constant.ConstantTable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@jakarta.persistence.Table(name = ConstantTable.TRANSACTIONBILL)
public class TransactionBill {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.enigma.challengeshop.utils.TransactionBillCustomId")
    @Column(name = "trx_id")
    private String trxId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

    @ManyToOne
    @JoinColumn(name = "trans_type_id")
    private TransType transType;

    @OneToMany(mappedBy = "trx")
    @JsonManagedReference
    private List<TransactionBillDetail> transactionBillDetails;

    @Temporal(TemporalType.DATE)
    @Column(name = "trans_date", updatable = false)
    private Date transDate;
}
