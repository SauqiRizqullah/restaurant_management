package com.enigma.challengeshop.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCustomerRequest {
    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;

    private String customerName;
}
