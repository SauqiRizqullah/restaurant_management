package com.enigma.challengeshop.service.impl;

import com.enigma.challengeshop.dto.request.NewCustomerRequest;
import com.enigma.challengeshop.dto.request.SearchCustomerRequest;
import com.enigma.challengeshop.dto.response.CustomerResponse;
import com.enigma.challengeshop.entity.Customer;
import com.enigma.challengeshop.repository.CustomerRepository;
import com.enigma.challengeshop.service.CustomerService;
import com.enigma.challengeshop.service.UserService;
import com.enigma.challengeshop.specification.CustomerSpecification;
import com.enigma.challengeshop.utils.ValidationUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;
    private final UserService userService;


//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public Customer createNewCustomer(NewCustomerRequest request) {
//        validationUtil.validate(request);
//
//        //Membuat objek Customer
//        Customer newCustomer = Customer.builder()
//                .customerName(request.getCustomerName())
//                .mobilePhoneNo(request.getMobilePhoneNo())
//                .isMember(request.getIsMember())
//                .build();
//
//        return customerRepository.saveAndFlush(newCustomer);
//    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteByCustomerId(String customerId) {
       Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer with Id " + customerId + " was not there!!!") );
       customerRepository.delete(customer);
       return customerId + " are not part of our business again";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updatePhoneByCustomerId(String newMobilePhoneNo, String customerId) {
        //1. Buatlah sebuah objek dimana Id customer tersebut ada atau tidak
        Customer currentCustomer = customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Id tidak ditemukan"));

        //2. Ubah nilai nomor telepon objek customer sebelumnya
        currentCustomer.setMobilePhoneNo(newMobilePhoneNo);

        //3. Jangan lupa lakukan query dari repositorynya untuk update pada databasenya
        customerRepository.updatePhoneNoByCustomerId(newMobilePhoneNo, customerId);

        customerRepository.saveAndFlush(currentCustomer);

        return "The Mobile Phone Number has changed to " + newMobilePhoneNo;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse createNewCustomerV2(NewCustomerRequest customerRequest) {

        validationUtil.validate(customerRequest);

        // 1. Membuat objek Customer
        Customer customer = Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .mobilePhoneNo(customerRequest.getMobilePhoneNo())
                .isMember(customerRequest.getIsMember())
                .build();

        // 2. Menyimpan data di database (saveandFlush)
        customerRepository.saveAndFlush(customer);
        return parseCustomerToCustomerResponse(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerByIdV2(String customerId) {
        // 1. Mencari objek customer dengan or else throw


        return customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Customer> getAllCustomerV2(SearchCustomerRequest searchCustomerRequest) {
        // 1. Mengnormalkan fungsi paging
        if (searchCustomerRequest.getPage() == 0) {
            searchCustomerRequest.setPage(1);
        }

        // 2. Membuat Aturan Sort

        String validSortBy;
        if("customerName".equalsIgnoreCase(searchCustomerRequest.getSortBy()) || "mobilePhoneNo".equalsIgnoreCase(searchCustomerRequest.getSortBy()) || "isMember".equalsIgnoreCase(searchCustomerRequest.getSortBy())){
            validSortBy = searchCustomerRequest.getSortBy();
        } else {
            validSortBy = "customerId";
        }

        // 3. Membuat direction dari objek Sort

        Sort sort = Sort.by(Sort.Direction.fromString(searchCustomerRequest.getDirection()), validSortBy);

        // 4. Membuat halaman secara keseluruhan

        Pageable pageable = PageRequest.of(searchCustomerRequest.getPage() - 1, searchCustomerRequest.getSize(), sort);

        // 5. Membuat specification karena query harus sesuai aturan criteria builder
        Specification<Customer> specification = CustomerSpecification.getSpecification(searchCustomerRequest);

        return customerRepository.findAll(specification,pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer createForUserAccount(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse updateCustomer(Customer customer) {
        // 1. cari idnya
        Customer updatedCustomer = customerRepository.findById(customer.getCustomerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        Customer newCustomer = Customer.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .mobilePhoneNo(customer.getMobilePhoneNo())
                .isMember(customer.getIsMember())
                .userAccount(customer.getUserAccount())
                .build();

        customerRepository.saveAndFlush(newCustomer);

        return parseCustomerToCustomerResponse(newCustomer);
    }

    public CustomerResponse parseCustomerToCustomerResponse(Customer customer) {
        String id;
        if(customer.getCustomerId() == null){
            id = null;
        } else {
            id = customer.getCustomerId();
        }

        return CustomerResponse.builder()
                .customerId(id)
                .customerName(customer.getCustomerName())
                .mobilePhoneNo(customer.getMobilePhoneNo())
                .isMember(customer.getIsMember())
                .build();
    }
}
