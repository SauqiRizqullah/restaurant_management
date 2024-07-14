package com.enigma.challengeshop.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerResponse {
    String customerId;
    String customerName;
    String mobilePhoneNo;
    Boolean isMember;
    String userAccountId;

}
