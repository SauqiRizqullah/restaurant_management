package com.enigma.challengeshop.controller;

import com.enigma.challengeshop.constant.APIUrl;
import com.enigma.challengeshop.dto.request.NewCustomerRequest;
import com.enigma.challengeshop.dto.request.SearchCustomerRequest;
import com.enigma.challengeshop.dto.response.CommonResponse;
import com.enigma.challengeshop.dto.response.CustomerResponse;
import com.enigma.challengeshop.dto.response.PagingResponse;
import com.enigma.challengeshop.entity.Customer;
import com.enigma.challengeshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

//    @PostMapping
//    public Customer createNewCustomer(
//            @RequestBody NewCustomerRequest customerRequest
//            ){
//        return customerService.createNewCustomer(customerRequest);
//    }

//    @GetMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID)
//    public Customer getCustomerById (
//            @PathVariable String customerId
//    ) {
//        return customerService.getByCustomerId(customerId);
//    }
//
//    @GetMapping
//    public Page<Customer> getAllCustomer (
//            @RequestParam(name = "page", defaultValue = "1") Integer page,
//            @RequestParam(name = "size", defaultValue = "10") Integer size,
//            @RequestParam(name = "sortBy", defaultValue = "customerId") String sortBy,
//            @RequestParam(name = "direction",defaultValue = "ASC") String direction,
//            @RequestParam(name = "customerName", required = false) String customerName
//            ) {
//        SearchCustomerRequest request = SearchCustomerRequest.builder()
//                .page(page)
//                .size(size)
//                .sortBy(sortBy)
//                .direction(direction)
//                .customerName(customerName)
//                .build();
//        return customerService.getAllCustomer(request);
//    }

//    @DeleteMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID)
//    public void deleteByCustomerId (
//            @PathVariable String customerId){
//        customerService.deleteByCustomerId(customerId);
//
//    }

//    @PutMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID)
//    public String updatePhoneNoByCustomerId (
//            @RequestParam(name = "mobilePhoneNo") String newMobilePhoneNo,
//            @PathVariable String customerId
//
//    ) {
//        customerService.updatePhoneByCustomerId(newMobilePhoneNo, customerId);
//        return customerId + " ID's mobile phone has been updated";
//    }

    //---------------ENTITY RESPONSE----------------------

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<CustomerResponse>> createNewCustomerV2 (
            @RequestBody NewCustomerRequest customerRequest
    ){
        // 1. Membuat objek Customer response dari pemanggilan service
        CustomerResponse customerResponse = customerService.createNewCustomerV2(customerRequest);

        // 2. Membuat objek CommonResponse untuk melakukan Response

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("You have created new customer data succesfully!!!")
                .data(customerResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<Customer>> getCustomerByIdV2 (
            @PathVariable String customerId
    ) {
        // 1. Memanggil service customer untuk membuat objek
        Customer customer = customerService.getCustomerByIdV2(customerId);

        // 2. Membuat objek Common Response
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here is the data of " + customerId)
                .data(customer)
                .build();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<Page<Customer>>> getAllCustomersV2 (
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "customerId") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "customerName", required = false) String customerName
    ) {
        // 1. Membuat objek Search Customer Request
        SearchCustomerRequest searchCustomerRequest = SearchCustomerRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .customerName(customerName)
                .build();

        // 2. Membuat Page Customer
        Page<Customer> allCustomers = customerService.getAllCustomerV2(searchCustomerRequest);

        // 3. Membuat Objek PagingResponse
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allCustomers.getTotalPages())
                .totalElements(allCustomers.getTotalElements())
                .page(allCustomers.getPageable().getPageNumber()+1)
                .size(allCustomers.getPageable().getPageSize())
                .hasNext(allCustomers.hasNext())
                .hasPrevious(allCustomers.hasPrevious())
                .build();

        // 4. Membuat Objek CommonResponse
        CommonResponse<Page<Customer>> response = CommonResponse.<Page<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("There you are. All customers data are here.")
                .data(allCustomers)
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> updateMobilePhoneByCustomerIdV2(
            @RequestParam(name = "mobilePhoneNo") String newPhoneNo,
            @PathVariable String customerId
    ) {
        // 1. Memanggil Service untuk melakukan update
        String dataUpdate = customerService.updatePhoneByCustomerId(newPhoneNo, customerId);

        // 2. Membuat Common Response

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(customerId + "'s mobile phone number has been updated")
                .data(dataUpdate)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_CUSTOMER_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> deleteCustomerByIdV2(
            @PathVariable String customerId
    ) {
        // 1. Memanggil service untuk menghapus customer
        String dataDelete = customerService.deleteByCustomerId(customerId);

        // 2. Memanggil Common Response
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(customerId + "'s data has been deleted")
                .data(dataDelete)
                .build();
        return ResponseEntity.ok(response);
    }

    //Update khusus karena ada akun
    @PutMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomer (
            @RequestBody Customer customer){
        CustomerResponse customerResponse = customerService.updateCustomer(customer);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Customer's data has been updated")
                .data(customerResponse)
                .build();

        return ResponseEntity.ok(response);
    }




    /*
        SERVICE FORMAT
    1. Create       Output: Response                            Input: NewRequest
    2. SearchbyID   Output: Response                            Input: String Id
    3. SearchAll    Output: List<Response> / Page<Entity>       Input: SearchRequest
    4. UpdatebyId   Output: String                              Input: String Id / Nilai lainnya
    5. DeletebyId   Output: String                              Input: String Id

        CONTROLLER FORMAT
    1. Create       Output: ResponseEntity<CommonResponse<Response>>        Input: @RequestBody NewRequest
    2. SearchbyID   Output: ResponseEntity<CommonResponse<Response>>        Input: @PathVariable String Id
    3. SearchAll    Output: ResponseEntity<CommonResponse<List<Response>>>  Input: @RequestParam Page / {Kolom lain dari entity itu}
    4. UpdatebyId   Output: ResponseEntity<CommonResponse<String>>          Input: @PathVariable String Id, @RequestParam nilaiYangMauDiubah
    5. DeletebyId   Output: ResponseEntity<CommonResponse<String>>          Input: @PathVariable String Id

     */

    //PR
    //
}
