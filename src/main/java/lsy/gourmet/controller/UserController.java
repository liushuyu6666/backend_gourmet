package lsy.gourmet.controller;


import lsy.gourmet.core.JWT;
import lsy.gourmet.model.loginrequest.LoginRequestBody;
import lsy.gourmet.model.user.User;
import lsy.gourmet.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lsy.gourmet.response.ResponseBody;

@RequestMapping("/v1")
@RestController
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JWT jwt;

    @PostMapping("/register")
    public ResponseEntity<ResponseBody> createUser(@RequestBody User userBody){
        try{
            User newUser = userService.createUser(userBody);
            ResponseBody<User> responseBody =
                    new ResponseBody(newUser, "create a new user", null);
            return ResponseEntity.ok(responseBody);
        }
        catch(Exception e){
            ResponseBody responseBody =
                    new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBody> login(@RequestBody LoginRequestBody loginRequestBody){
        try{
            String token = userService.createToken(loginRequestBody);
            ResponseBody responseBody = new ResponseBody(token,
                    "token has been wrapped in it", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(null,
                    e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ResponseBody> checkProfile(@RequestHeader("token") String token){
        try{
            String username = jwt.verifyToken(token);
            User targetUser = userService.retrieveUser(username);
            ResponseBody responseBody = new ResponseBody(targetUser,
                    "get user information", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(null,
                    e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @PostMapping("/profile")
    public ResponseEntity<ResponseBody> updateProfile(
            @RequestHeader("token") String token,
            @RequestBody User updatingUser
    ){
        try{
            String username = jwt.verifyToken(token);
            User updatedUser = userService.updateUser(username, updatingUser);
            ResponseBody responseBody = new ResponseBody(updatedUser,
                    "user has been updated", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody = new ResponseBody(null,
                    e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

}
