package com.enigma.challengeshop.entity;

import com.enigma.challengeshop.constant.ConstantTable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.MENU)
public class Menu {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.enigma.challengeshop.utils.MenuCustomId")
    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "menu_price", nullable = false)
    @Min(value = 0, message = "Price is not allowed set up below 0")
    private Long menuPrice;

//    @PostMapping(consumes = MediaType.)
//    public String get(){
//        return null
//    }
}
