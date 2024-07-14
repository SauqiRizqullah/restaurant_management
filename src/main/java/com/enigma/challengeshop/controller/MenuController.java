package com.enigma.challengeshop.controller;

import com.enigma.challengeshop.constant.APIUrl;
import com.enigma.challengeshop.dto.request.NewMenuRequest;
import com.enigma.challengeshop.dto.request.SearchMenuRequest;
import com.enigma.challengeshop.dto.response.CommonResponse;
import com.enigma.challengeshop.dto.response.MenuResponse;
import com.enigma.challengeshop.dto.response.PagingResponse;
import com.enigma.challengeshop.entity.Menu;
import com.enigma.challengeshop.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.MENU_API)
public class MenuController {
    private final MenuService menuService;

//    @PostMapping
//    public Menu createNewMenu(@RequestBody Menu menu){
//        return menuService.createNewMenu(menu);
//    }

//    @GetMapping(path = APIUrl.PATH_VAR_MENU_ID)
//    public Menu getByMenuId (@PathVariable String menuId){
//        return menuService.getByMenuId(menuId);
//    }


//    @DeleteMapping(path = APIUrl.PATH_VAR_MENU_ID)
//    public void deleteMenuById (@PathVariable String menuId){
//        menuService.deleteByMenuId(menuId);
//    }
//
//    @GetMapping
//    public Page<Menu> getAllMenu (
//            @RequestParam(name = "page", defaultValue = "1") Integer page,
//            @RequestParam(name = "size", defaultValue = "10") Integer size,
//            @RequestParam(name = "sortBy", defaultValue = "menuId") String sortBy,
//            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
//            @RequestParam(name = "menuName", required = false) String menuName
//    ){
//        SearchMenuRequest request = SearchMenuRequest.builder()
//                .page(page)
//                .size(size)
//                .sortBy(sortBy)
//                .direction(direction)
//                .menuName(menuName)
//                .build();
//        return menuService.getAllMenu(request);
//    }

//    @PutMapping(path = APIUrl.UPDATE_MENU_PRICE)
//    public String updatePriceByMenuName (
//            @RequestParam("menuName") String menuName,
//            @RequestParam("menuPrice") Long newMenuPrice){
//        menuService.updatePriceByMenuName(menuName, newMenuPrice);
//        return "Menu " + menuName + " telah berubah harganya, silahkan dilihat daftar menunya kembali";
//    }

    //----------------ENTITYRESPONSE----------------------------

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<MenuResponse>> createNewMenuVersionTwo (
            @RequestBody NewMenuRequest menuRequest
    ){
        // 1. Membuat objek Menu response dari pemanggilan service
        MenuResponse menuResponse = menuService.createNewMenuV2(menuRequest);

        // 2. Membuat objek CommonResponse untuk melakukan Response

        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("You have created new menu succesfully!!!")
                .data(menuResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping(path = APIUrl.PATH_VAR_MENU_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<Menu>> getMenuByIdV2(
            @PathVariable String menuId
    ) {
        // 1. Membuat objek Menu Response
        Menu menu = menuService.getMenuByIdV2(menuId);

        // 2. Membuat objek Common Response untuk mengisi data response
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message(menuId + "'s data was attrieved")
                .data(menu)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<Page<Menu>>> getAllMenusV2 (
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "menuId") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "menuName", required = false) String menuName
    ){
        // 1. Membuat objek SearchMenuRequest untuk mencari Menu semuanya
        SearchMenuRequest searchMenuRequest = SearchMenuRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .menuName(menuName)
                .build();

        // 2. Membuat objek Page Menu
        Page<Menu> allMenu = menuService.getAllMenusV2(searchMenuRequest);

        // 3. Membuat objek paging
        PagingResponse pagingResponse = PagingResponse.builder()
                .page(allMenu.getPageable().getPageNumber() + 1)
                .size(allMenu.getPageable().getPageSize())
                .totalPages(allMenu.getTotalPages())
                .totalElements(allMenu.getTotalElements())
                .hasNext(allMenu.hasNext())
                .hasPrevious(allMenu.hasPrevious())
                .build();

        // 4. Membuat objek Common Response untuk response
        CommonResponse<Page<Menu>> response = CommonResponse.<Page<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Retrieved all data successfully")
                .data(allMenu)
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.UPDATE_MENU_PRICE, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> updateByMenuName(
            @RequestParam(name = "menuPrice") Long newMenuPrice,
            @RequestParam(name = "menuName") String menuName
            ){

        // 1. Memanggil service untuk melakukan update data menu
        String dataUpdate = menuService.updatePriceByMenuName(menuName, newMenuPrice);

        // 2. Membuat Common Response

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(menuName + "'s price has been updated successfully")
                .data(dataUpdate)
                .build();

        return ResponseEntity.ok(response);

    }

    @DeleteMapping(path = APIUrl.PATH_VAR_MENU_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> deleteMenuByIdV2 (
            @PathVariable String menuId
    ){
        // 1. Memanggil service untuk menghapus objek menu
        String dataDelete = menuService.deleteByMenuId(menuId);

        // 2. Membuat Common Response
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(menuId + "'s data has been deleted from menu list")
                .data(dataDelete)
                .build();

        return ResponseEntity.ok(response);
    }




    /*
        SERVICE FORMAT
    1. Create       Output: Response                            Input: NewRequest
    2. SearchbyID   Output: Response                            Input: String Id
    3. SearchAll    Output: List<Response> / Page<Entity>       Input: SearchRequest
    4. UpdatebyId   Output: String                              Input: String Id / Nilai lainnya
    5. DeletebyId   Output: String                              Input: String Id

        CONTROLLER FORMAT
    1. Create       Output: ResponseEntity<CommonResponse<Response>>         Input: @RequestBody NewRequest
    2. SearchbyID   Output: ResponseEntity<CommonResponse<Response>>         Input: @PathVariable String Id
    3. SearchAll    Output: ResponseEntity<CommonResponse<List<Response>>>  Input: @RequestParam Page / {Kolom lain dari entity itu}
    4. UpdatebyId   Output: ResponseEntity<CommonResponse<String>>          Input: @PathVariable String Id, @RequestParam nilaiYangMauDiubah
    5. DeletebyId   Output: ResponseEntity<CommonResponse<String>>          Input: @PathVariable String Id

     */

}
