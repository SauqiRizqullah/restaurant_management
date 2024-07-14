package com.enigma.challengeshop.service;

import com.enigma.challengeshop.dto.request.NewTableRequest;
import com.enigma.challengeshop.dto.request.SearchTableRequest;
import com.enigma.challengeshop.dto.response.TableResponse;
import com.enigma.challengeshop.entity.Table;

import java.util.List;

public interface TableService {

    //4. Delete by tableId
    String deleteByTableId(String tableId);



    TableResponse createNewTableV2(NewTableRequest tableRequest);

    Table getTableByIdV2(String tableId);

    List<TableResponse> getAllTablesV2(SearchTableRequest tableRequest);

    String updateTableByIdV2(String newTableName, String tableId);
}
