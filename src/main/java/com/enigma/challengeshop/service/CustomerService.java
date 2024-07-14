package com.enigma.challengeshop.service;

import com.enigma.challengeshop.dto.request.NewCustomerRequest;
import com.enigma.challengeshop.dto.request.SearchCustomerRequest;
import com.enigma.challengeshop.dto.response.CustomerResponse;
import com.enigma.challengeshop.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    //4. Delete byCustomerId
    String deleteByCustomerId (String customerId);
    //5. Update Mobile Phone by Customer Id
    String updatePhoneByCustomerId (String mobilePhoneNo, String customerId);

    CustomerResponse createNewCustomerV2(NewCustomerRequest customerRequest);

    Customer getCustomerByIdV2(String customerId);

    Page<Customer> getAllCustomerV2(SearchCustomerRequest searchCustomerRequest);

    Customer createForUserAccount(Customer customer);

    CustomerResponse updateCustomer(Customer customer);
}

