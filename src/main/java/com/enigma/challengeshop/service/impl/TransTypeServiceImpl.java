package com.enigma.challengeshop.service.impl;

import com.enigma.challengeshop.dto.request.NewTransTypeRequest;
import com.enigma.challengeshop.dto.request.SearchTransTypeRequest;
import com.enigma.challengeshop.dto.response.TransTypeResponse;
import com.enigma.challengeshop.entity.TransType;
import com.enigma.challengeshop.repository.TransTypeRepository;
import com.enigma.challengeshop.service.TransTypeService;
import com.enigma.challengeshop.specification.TransTypeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransTypeServiceImpl implements TransTypeService {
    private final TransTypeRepository transTypeRepository;


    @Transactional(readOnly = true)
    @Override
    public TransType getTransTypeById(String transTypeId) {
        // Mencari Objek dengan Id yang sudah ada dengan metode or else throw

        return transTypeRepository.findById(transTypeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));
    }

    public TransTypeResponse parseTransTypeToTransTypeResponse(TransType transType) {
        String id;
        if (transType.getTransTypeId() == null){
            id = null;
        } else {
            id = transType.getTransTypeId();
        }

        return TransTypeResponse.builder()
                .transTypeId(id)
                .description(transType.getDescription())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateTransTypeById(String newDescription, String transTypeId) {
        //1. Mencari objek dengan transTypeId dengan metode orElseThrow
        TransType transType = transTypeRepository.findById(transTypeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        //2. Memperbarui nilai deskripsi dengan setter
        transType.setDescription(newDescription);

        //3. Jangan lupa lakukan query native dan melakukan penyimpanan data di database (saveAndFlush) melalui repository
        transTypeRepository.updateTransTypeById(newDescription, transTypeId);
        transTypeRepository.saveAndFlush(transType);

        return "Therefore, the new description is " + newDescription;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteByTransTypeId(String transTypeId) {
        TransType transType = transTypeRepository.findById(transTypeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id was not there"));

        transTypeRepository.delete(transType);

        return "Therefore, you must use another trans type";
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransTypeResponse> getAllTransTypes(SearchTransTypeRequest transTypeRequest) {
            // 1. Menambahkan specification dalam melakukan query
            Specification<TransType> transTypeSpecification = TransTypeSpecification.getSpecification(transTypeRequest);
            // 2. Jika tidak memasukkan specification untuk melakukan query, maka query normal saja, tapi di parse dulu ke responsenya
            if (transTypeRequest.getDescription() == null){
                return transTypeRepository.findAll().stream().map(this::parseTransTypeToTransTypeResponse).toList();
            } else
            // 3.
                return transTypeRepository.findAll(transTypeSpecification).stream().map(this::parseTransTypeToTransTypeResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransTypeResponse createNewTableV2(NewTransTypeRequest transTypeRequest) {
        // 1. Membuat objek TransType
        TransType transType = TransType.builder()
                .description(transTypeRequest.getDescription())
                .build();
        // 2. Melakukan penyimpanan data
        transTypeRepository.saveAndFlush(transType);
        return parseTransTypeToTransTypeResponse(transType);
    }
}
