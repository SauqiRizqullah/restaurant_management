package com.enigma.challengeshop.specification;

import com.enigma.challengeshop.dto.request.SearchCustomerRequest;
import com.enigma.challengeshop.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (request.getCustomerName() != null){
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("customer_name")), "%" + request.getCustomerName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }

}
