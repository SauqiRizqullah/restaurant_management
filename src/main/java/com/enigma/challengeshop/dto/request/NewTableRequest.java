package com.enigma.challengeshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTableRequest {
    @NotBlank(message = "Table name can't be empty")
    private String tableName;
}
