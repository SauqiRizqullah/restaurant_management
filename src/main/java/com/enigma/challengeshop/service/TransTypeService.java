package com.enigma.challengeshop.service;

import com.enigma.challengeshop.dto.request.NewTransTypeRequest;
import com.enigma.challengeshop.dto.request.SearchTransTypeRequest;
import com.enigma.challengeshop.dto.response.TransTypeResponse;
import com.enigma.challengeshop.entity.TransType;

import java.util.List;

public interface TransTypeService {

    //2. Select byTransTypeId
    TransType getTransTypeById (String transTypeId);
    //3. Update descriptionById
    String updateTransTypeById(String newDescription, String transTypeId);
    //4. Delete byTransTypeId
    String deleteByTransTypeId(String transTypeId);
    //5. Select AllTransType
    List<TransTypeResponse> getAllTransTypes (SearchTransTypeRequest transTypeRequest);

    TransTypeResponse createNewTableV2(NewTransTypeRequest transTypeRequest);

}
