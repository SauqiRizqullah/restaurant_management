package com.enigma.challengeshop.entity;

import com.enigma.challengeshop.constant.ConstantTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@jakarta.persistence.Table(name = ConstantTable.TABLE)
public class Table {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.enigma.challengeshop.utils.TableCustomId")
    @Column(name = "table_id")
    private String tableId;

    @Column(name = "table_name")
    @NotBlank(message = "Table can't be empty")
    private String tableName;
}
