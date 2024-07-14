package com.enigma.challengeshop.specification;

import com.enigma.challengeshop.dto.request.SearchTransTypeRequest;
import com.enigma.challengeshop.entity.TransType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransTypeSpecification {
    public static Specification<TransType> getSpecification (SearchTransTypeRequest transTypeRequest){
        // 1. Mengembalikan root (select), query, dan criteria builder (where, like, etc)
        return (root, query, criteriaBuilder) -> {

            // 2. Membuat list predicates untuk menampung query criteria builder
            List<Predicate> predicates = new ArrayList<>();
            if (transTypeRequest.getDescription() != null){
                Predicate transTypePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + transTypeRequest.getDescription().toLowerCase() + "%");
                predicates.add(transTypePredicate);
            }
            // 3. Mengembalikan query dengan criteria di atas
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
