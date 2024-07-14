package com.enigma.challengeshop.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MenuResponse {
    String menuId;
    String menuName;
    Long menuPrice;
}
