package com.enigma.challengeshop.repository;

import com.enigma.challengeshop.entity.TransType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TransTypeRepository extends JpaRepository<TransType, String>, JpaSpecificationExecutor<TransType> {

    @Modifying
    @Query(value = "UPDATE m_trans_type SET description = :description WHERE trans_type_id = :transTypeId", nativeQuery = true)
    void updateTransTypeById(@Param("description") String newDescription, @Param("transTypeId") String transTypeId);
}
