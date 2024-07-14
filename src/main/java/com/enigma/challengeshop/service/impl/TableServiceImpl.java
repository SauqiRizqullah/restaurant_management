package com.enigma.challengeshop.service.impl;

import com.enigma.challengeshop.dto.request.NewTableRequest;
import com.enigma.challengeshop.dto.request.SearchTableRequest;
import com.enigma.challengeshop.dto.response.TableResponse;
import com.enigma.challengeshop.entity.Table;
import com.enigma.challengeshop.repository.TableRepository;
import com.enigma.challengeshop.service.TableService;
import com.enigma.challengeshop.specification.TableSpecification;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteByTableId(String tableId) {
        Table table = tableRepository.findById(tableId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "That table Id was not there"));
        tableRepository.delete(table);
        return tableId + " is not in property list anymore";
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public TableResponse createNewTableV2(NewTableRequest tableRequest) {


        // 1. Membuat objek Table

        Table table = Table.builder()
                .tableName(tableRequest.getTableName())
                .build();

        // 2. Save ke data base dan parse ke tabel response
        tableRepository.saveAndFlush(table);
        return parseTableToTableResponse(table);
    }

    @Transactional(readOnly = true)
    @Override
    public Table getTableByIdV2(String tableId) {
        // 1. Mencari table Id dengan or else throw

        return tableRepository.findById(tableId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<TableResponse> getAllTablesV2(SearchTableRequest tableRequest) {
        Specification<Table> tableSpecification = TableSpecification.getSpecification(tableRequest);
        if(tableRequest.getTableName() == null){
            return tableRepository.findAll().stream().map(this::parseTableToTableResponse).toList();
        }
        return tableRepository.findAll(tableSpecification).stream().map(this::parseTableToTableResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateTableByIdV2(String newTableName, String tableId) {
        // 1. Mencari objek yang ada

        Table table = tableRepository.findById(tableId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        // 2. Mengubah nilai tableName dengan setter

        table.setTableName(newTableName);

        // 3. Melakukan query dan menyimpan di database

        tableRepository.updateTableNameById(newTableName, tableId);
        tableRepository.saveAndFlush(table);
        return tableId + "'s name is turned into " + newTableName;
    }

    public TableResponse parseTableToTableResponse (Table table){
        String tableId;
        if(table.getTableId() == null){
            tableId = null;
        } else
            tableId = table.getTableId();
        return TableResponse.builder()
                .tableId(tableId)
                .tableName(table.getTableName())
                .build();
    }

}
