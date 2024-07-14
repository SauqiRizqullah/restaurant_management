package com.enigma.challengeshop.entity;


import com.enigma.challengeshop.constant.ConstantTable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.TRANSACTIONBILLDETAIL)
public class TransactionBillDetail {
//PR
    //Buat entity - Service khusus transaction sampe transaction detail
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.enigma.challengeshop.utils.TransactionBillDetailCustomId")
    @Column(name = "trx_detail_id")
    private String trxDetailId;

    @ManyToOne
    @JoinColumn(name = "trx_id",nullable = false)
    @JsonManagedReference
    private TransactionBill trx;

    @ManyToOne
    @JoinColumn(name = "menu_id",nullable = false)
    private Menu menu;

    @Column(name = "menu_price", nullable = false, updatable = false)
    private Long menuPrice;

    @Column(name = "qty", nullable = false)
    private Integer qty;
}

//PR
//1. coba cek Requestnya TransDetail, lengkapin yang merah", terus jalanin springbootnya
//2. cek sql
