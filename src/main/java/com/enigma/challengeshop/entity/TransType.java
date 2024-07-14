package com.enigma.challengeshop.entity;

import com.enigma.challengeshop.constant.ConstantTable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.TRANSTYPE)
public class TransType {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.enigma.challengeshop.utils.TransTypeCustomId")
    @Column(name = "trans_type_id")
    private String transTypeId;

    @Column(name = "description")
    @NotBlank(message = "Trans type can't be empty")
    private String description;
}
