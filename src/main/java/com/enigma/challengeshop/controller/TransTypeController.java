package com.enigma.challengeshop.controller;

import com.enigma.challengeshop.constant.APIUrl;
import com.enigma.challengeshop.dto.request.NewTransTypeRequest;
import com.enigma.challengeshop.dto.request.SearchTransTypeRequest;
import com.enigma.challengeshop.dto.response.CommonResponse;
import com.enigma.challengeshop.dto.response.TableResponse;
import com.enigma.challengeshop.dto.response.TransTypeResponse;
import com.enigma.challengeshop.entity.TransType;
import com.enigma.challengeshop.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANS_TYPE_API)
public class TransTypeController {
    private final TransTypeService transTypeService;

//    @PostMapping
//    public TransType createNewTransType (
//            @RequestBody NewTransTypeRequest transTypeRequest){
//
//        return transTypeService.createNewTransType(transTypeRequest);
//    }

//    @GetMapping(path = APIUrl.PATH_VAR_TRANS_TYPE_ID)
//    public TransTypeResponse getTransTypeById (
//            @PathVariable String transTypeId){
//        return transTypeService.getTransTypeById(transTypeId);
//    }
//
//    @PutMapping(path = APIUrl.PATH_VAR_TRANS_TYPE_ID)
//    public String updateTransTypeById (
//            @RequestParam(name = "description") String newDescription,
//            @PathVariable String transTypeId
//    ){
//        transTypeService.updateTransTypeById(newDescription, transTypeId);
//        return transTypeId + "'s description has been updated";
//    }

//    @DeleteMapping(path = APIUrl.PATH_VAR_TRANS_TYPE_ID)
//    public String deleteTransTypeById (
//            @PathVariable String transTypeId
//    ) {
//        transTypeService.deleteByTransTypeId(transTypeId);
//        return transTypeId + "'s data has been deleted forever";
//    }

//    @GetMapping
//    public List<TransTypeResponse> getAllTransTypes (
//            @RequestParam(name = "description", required = false) String description
//    ) {
//
//        SearchTransTypeRequest request = SearchTransTypeRequest.builder()
//                .description(description)
//                .build();
//
//        return transTypeService.getAllTransTypes(request);
//    }

    //-----------------------ENTITY RESPONSE------------------------

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<TransTypeResponse>> createNewTransTypeV2(
            @RequestBody NewTransTypeRequest transTypeRequest
    ) {
        TransTypeResponse transTypeResponse = transTypeService.createNewTableV2(transTypeRequest);

        CommonResponse<TransTypeResponse> response = CommonResponse.<TransTypeResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("New transaction type has been created")
                .data(transTypeResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_TRANS_TYPE_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<TransType>> getTransTypeByIdV2 (
            @PathVariable String transTypeId
    ) {
        // 1. Memanggil objek dengan Idnya
        TransType transType = transTypeService.getTransTypeById(transTypeId);

        CommonResponse<TransType> response = CommonResponse.<TransType>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here is your request")
                .data(transType)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<List<TransTypeResponse>>> getAllTransTypesV2 (
            @RequestParam(name = "description", required = false) String description
    ) {
        SearchTransTypeRequest request = SearchTransTypeRequest.builder()
                .description(description)
                .build();

        List<TransTypeResponse> allTransTypes = transTypeService.getAllTransTypes(request);

        CommonResponse<List<TransTypeResponse>> response = CommonResponse.<List<TransTypeResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here's your request")
                .data(allTransTypes)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_TRANS_TYPE_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> updateTransTypeByIdV2(
            @RequestParam(name = "description") String newDescription,
            @PathVariable String transTypeId
    ) {
        String dataUpdate = transTypeService.updateTransTypeById(newDescription, transTypeId);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(transTypeId + "'s description has been updated a second ago")
                .data(dataUpdate)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_TRANS_TYPE_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> deleteTransTypeByIdV2 (
            @PathVariable String transTypeId
    ) {
        String dataDelete = transTypeService.deleteByTransTypeId(transTypeId);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(transTypeId + "'s data has been removed from database")
                .data(dataDelete)
                .build();

        return ResponseEntity.ok(response);
    }

    //Konsep

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
