package com.enigma.challengeshop.repository;

import com.enigma.challengeshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    @Modifying
    @Query(value = "UPDATE m_customer SET mobile_phone_no = :mobilePhoneNo WHERE customer_id = :customerId", nativeQuery = true)
    void updatePhoneNoByCustomerId (@Param("mobilePhoneNo") String newMobilePhoneNo,@Param("customerId") String customerId);
}
