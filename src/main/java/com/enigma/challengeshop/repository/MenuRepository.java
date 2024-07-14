package com.enigma.challengeshop.repository;

import com.enigma.challengeshop.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
    @Modifying
    @Query(value = "UPDATE m_menu SET menu_price = :menuPrice WHERE menu_name = :menuName", nativeQuery = true)
    void updateMenuPriceByMenuId(@Param("menuName") String menuName, @Param("menuPrice") Long newMenuPrice);

    @Query(value = "SELECT * FROM m_menu WHERE menu_name = :menuName", nativeQuery = true)
    Optional<Menu> getByMenuName(@Param("menuName") String menuName);
}
