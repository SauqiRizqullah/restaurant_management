package com.enigma.challengeshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTransTypeRequest {
    @NotBlank(message = "Description can't be empty")
    private String description;
}
