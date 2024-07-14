package com.enigma.challengeshop.specification;

import com.enigma.challengeshop.dto.request.SearchTableRequest;
import com.enigma.challengeshop.entity.Table;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TableSpecification {
    public static Specification<Table> getSpecification (SearchTableRequest request){
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (request.getTableName() != null){
                Predicate tablePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("tableName")), "%" + request.getTableName().toLowerCase() + "%");
                predicates.add(tablePredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        });

    }
}
