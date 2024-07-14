package com.enigma.challengeshop.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchMenuRequest {
    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;

    private String menuName;
}
