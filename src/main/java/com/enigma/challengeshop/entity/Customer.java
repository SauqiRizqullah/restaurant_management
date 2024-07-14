package com.enigma.challengeshop.entity;

import com.enigma.challengeshop.constant.ConstantTable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.enigma.challengeshop.utils.CustomerCustomId")
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "mobile_phone_no")
//    @NotBlank(message = "Every person must have mobile phone number to be contacted")
//    @Pattern(regexp = "^08\\d{9,11}$", message = "Mobile phone number must be valid and started with '08' followed by 9 until 11 digits.")
    private String mobilePhoneNo;

    @Column(name = "is_member")
    private Boolean isMember;

    @OneToOne // setiap customer memiliki satu akun
    @JoinColumn(name = "user_account_id", unique = true)
    private UserAccount userAccount;

}
