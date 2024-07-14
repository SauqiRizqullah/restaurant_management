package com.enigma.challengeshop.controller;

import com.enigma.challengeshop.constant.APIUrl;
import com.enigma.challengeshop.dto.request.NewTableRequest;
import com.enigma.challengeshop.dto.request.SearchTableRequest;
import com.enigma.challengeshop.dto.response.CommonResponse;
import com.enigma.challengeshop.dto.response.TableResponse;
import com.enigma.challengeshop.entity.Table;
import com.enigma.challengeshop.service.CustomerService;
import com.enigma.challengeshop.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.TABLE_API)
public class TableController {
    private final TableService tableService;

//    @PostMapping
//    public Table createNewTable (
//            @RequestBody Table table){
//        return tableService.createNewTable(table);
//    }

//    @GetMapping(path = APIUrl.PATH_VAR_TABLE_ID)
//    public TableResponse getByTableId (
//            @PathVariable String tableId){
//        return tableService.getByTableId(tableId);
//    }
//
//    @GetMapping
//    public List<TableResponse> getAllTables (
//            @RequestParam(name = "tableName", required = false) String tableName){
//        SearchTableRequest tableRequest = SearchTableRequest.builder()
//                .tableName(tableName)
//                .build();
//        return tableService.getAll(tableRequest);
//    }


//    @PutMapping(path = APIUrl.PATH_VAR_TABLE_ID)
//    public String updateTableNameById (
//            @RequestParam(name = "tableName") String newTableName,
//            @PathVariable String tableId
//    ) {
//        tableService.updateTableNameById(newTableName, tableId);
//        return tableId + "'s name has been updated";
//    }

//    @DeleteMapping(path = APIUrl.PATH_VAR_TABLE_ID)
//    public String deleteTable(
//            @PathVariable(name = "tableId") String tableId
//    ){
//        tableService.deleteByTableId(tableId);
//        return tableId + "'s data has been deleted";
//    }

    //-----------------------ENTITY RESPONSE-----------------------

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<TableResponse>> createNewTableV2 (
            @RequestBody NewTableRequest tableRequest
    ) {
        // 1. Membuat objek table Response
        TableResponse tableResponse = tableService.createNewTableV2(tableRequest);

        // 2. Membuat Common Response
        CommonResponse<TableResponse> response = CommonResponse.<TableResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("New data table has been created")
                .data(tableResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping(path = APIUrl.PATH_VAR_TABLE_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<Table>> getTableByIdV2 (
            @PathVariable String tableId
    ) {
        // 1. Memanggil service untuk mendapatkan objek response berdasarkan Id
        Table table = tableService.getTableByIdV2(tableId);

        // 2. Membuat Common Response
        CommonResponse<Table> response = CommonResponse.<Table>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Here's the " + tableId + "'s data")
                .data(table)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<List<TableResponse>>> getAllTablesV2 (
            @RequestParam(name = "tableName", required = false) String tableName
    ){
        // 1. Membuat objek search table
        SearchTableRequest searchTableRequest = SearchTableRequest.builder()
                .tableName(tableName)
                .build();

        // 2. Memanggil service untuk mengumpulkan semua objek table
        List<TableResponse> allTables = tableService.getAllTablesV2(searchTableRequest);

        // 3. Memanggil Common Response
        CommonResponse<List<TableResponse>> response = CommonResponse.<List<TableResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully retrieving table data")
                .data(allTables)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_TABLE_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> updateTableByIdV2(
            @RequestParam(name = "tableName") String newTableName,
            @PathVariable String tableId
    ) {
        // 1. Memanggil service untuk update
        String dataUpdate = tableService.updateTableByIdV2(newTableName, tableId);

        // 2. Membuat Common Response
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(tableId + "'s data has been updated a second ago")
                .data(dataUpdate)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_TABLE_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> deleteTableByIdV2 (
            @PathVariable String tableId
    ){
        // 1. Menghapus table
        String dataDelete = tableService.deleteByTableId(tableId);

        // 2. Membuat Common Response
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(tableId + "'s data has been removed")
                .data(dataDelete)
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
