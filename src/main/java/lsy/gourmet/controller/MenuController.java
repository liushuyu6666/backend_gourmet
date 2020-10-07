package lsy.gourmet.controller;

import lsy.gourmet.core.JWT;
import lsy.gourmet.model.menu.Menu;
import lsy.gourmet.model.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lsy.gourmet.response.ResponseBody;

import java.util.List;

@RequestMapping("/v1")
@RestController
@CrossOrigin(origins="*")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    JWT jwt;

    @PostMapping("/landings/{landing_id}/menus")
    public ResponseEntity<ResponseBody> createMenu(
            @RequestHeader String token,
            @RequestBody Menu menuBody,
            @PathVariable("landing_id") Integer landingId
    ){
        try{
            String username = jwt.verifyToken(token);
            Menu savedMenu = menuService.createMenu(menuBody, landingId, username);
            ResponseBody<Menu> responseBody = new ResponseBody(savedMenu, "create a new menu", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @GetMapping("/landings/{landing_id}/menus/{menu_id}")
    public ResponseEntity<ResponseBody> retrieveMenu(
            @RequestHeader String token,
            @PathVariable("menu_id") Integer id,
            @PathVariable("landing_id") Integer landingId
    ){
        try{
            String username = jwt.verifyToken(token);
            Menu foundMenu = menuService.retrieveMenu(id, landingId, username);
            ResponseBody<Menu> responseBody = new ResponseBody(foundMenu, "find menu", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @GetMapping("/landings/{landing_id}/menus")
    public ResponseEntity<ResponseBody> listMenu(
            @RequestHeader String token,
            @PathVariable("landing_id") Integer landingId
    ){
        try{
            String username = jwt.verifyToken(token);
            List<Menu> menuList = menuService.listMenu(landingId, username);
            ResponseBody<List> responseBody = new ResponseBody(menuList, "list all menus", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @PostMapping("/landings/{landing_id}/menus/{menu_id}")
    public ResponseEntity<ResponseBody> updateMenu(
            @RequestHeader String token,
            @RequestBody Menu menuBody,
            @PathVariable("menu_id") Integer id,
            @PathVariable("landing_id") Integer landingId
    ){
        try{
            String username = jwt.verifyToken(token);
            Menu updatedMenu = menuService.updateMenu(id, menuBody, landingId, username);
            ResponseBody<Menu> responseBody = new ResponseBody(updatedMenu, "update menu", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @DeleteMapping("/landings/{landing_id}/menus/{id}")
    public ResponseEntity<ResponseBody> createMenu(
            @RequestHeader String token,
            @PathVariable("id") Integer id,
            @PathVariable("landing_id") Integer landingId
    ){
        try{
            String username = jwt.verifyToken(token);
            Menu deleteMenu = menuService.deleteMenu(id, landingId, username);
            ResponseBody<Menu> responseBody = new ResponseBody(deleteMenu, "delete a menu", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }
}
