package com.enigma.challengeshop.repository;

import com.enigma.challengeshop.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TableRepository extends JpaRepository<Table, String>, JpaSpecificationExecutor<Table> {

    @Modifying
    @Query(value = "UPDATE m_table SET table_name = :tableName WHERE table_id = :tableId", nativeQuery = true)
    void updateTableNameById(@Param("tableName") String newTableName, @Param("tableId") String tableId);
}
