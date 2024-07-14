package com.enigma.challengeshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCustomerRequest {
    private String id;

    @NotBlank(message = "Every person has a name")
    private String customerName;

    @NotBlank(message = "Every person must have mobile phone number to be contacted")
    @Pattern(regexp = "^08\\d{9,11}$", message = "Mobile phone number must be valid and started with '08' followed by 9 until 11 digits.")
    private String mobilePhoneNo;

    private Boolean isMember;
}
