package com.enigma.challengeshop.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchTableRequest {
    private String tableName;
}
