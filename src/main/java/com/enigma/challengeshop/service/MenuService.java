package com.enigma.challengeshop.service;

import com.enigma.challengeshop.dto.request.NewMenuRequest;
import com.enigma.challengeshop.dto.request.SearchMenuRequest;
import com.enigma.challengeshop.dto.response.MenuResponse;
import com.enigma.challengeshop.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface MenuService {
    //1. Delete byMenuId
    String deleteByMenuId (String menuId);

    //2. Update Price by Menu Name
    String updatePriceByMenuName (String menuName, Long newMenuPrice);

    //3. Create New Menu
    MenuResponse createNewMenuV2(NewMenuRequest menuRequest);

    //4. Get Menu by Id
    Menu getMenuByIdV2(String menuId);

    //5. Get All Menu
    Page<Menu> getAllMenusV2(SearchMenuRequest searchMenuRequest);
}
