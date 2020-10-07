package lsy.gourmet.controller;

import lsy.gourmet.core.JWT;
import lsy.gourmet.model.landing.Landing;
import lsy.gourmet.model.landing.LandingRepository;
import lsy.gourmet.model.landing.LandingService;
import lsy.gourmet.model.user.User;
import lsy.gourmet.response.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
@CrossOrigin(origins="*")
public class LandingController {

    @Autowired
    LandingService landingService;

    @Autowired
    JWT jwt;

    @PostMapping("/landings")
    public ResponseEntity<ResponseBody> createLanding(@RequestBody Landing landingBody,
                                                      @RequestHeader String token){
        try{
            String username = jwt.verifyToken(token);
            Landing newLanding = landingService.createLanding(landingBody, username);
            ResponseBody<Landing> responseBody =
                    new ResponseBody(newLanding, "create a new landing", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody =
                    new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @GetMapping("/landings")
    public ResponseEntity<ResponseBody> listLanding(@RequestHeader String token){
        try{
            String username = jwt.verifyToken(token);
            List<Landing> landingList = landingService.listLanding(username);
            ResponseBody<List> responseBody =
                    new ResponseBody(landingList, "list all landings", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody =
                    new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @GetMapping("/landings/{id}")
    public ResponseEntity<ResponseBody> retrieveLanding(
            @RequestHeader String token,
            @PathVariable Integer id
    ){
        try{
            String username = jwt.verifyToken(token);
            Landing targetLanding = landingService.retrieveLanding(id, username);
            ResponseBody<Landing> responseBody =
                    new ResponseBody(targetLanding, "retrieve target landing", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody =
                    new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @PostMapping("/landings/{id}")
    public ResponseEntity<ResponseBody> updateLanding(
            @RequestHeader String token,
            @RequestBody Landing landingBody,
            @PathVariable Integer id
    ){
        try{
            String username = jwt.verifyToken(token);
            Landing targetLanding = landingService.updateLanding(id, landingBody, username);
            ResponseBody<Landing> responseBody =
                    new ResponseBody(targetLanding, "update target landing", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody =
                    new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

    @DeleteMapping("/landings/{id}")
    public ResponseEntity<ResponseBody> deleteLanding(
            @RequestHeader String token,
            @PathVariable Integer id
    ){
        try{
            String username = jwt.verifyToken(token);
            Landing targetLanding = landingService.deleteLanding(id, username);
            ResponseBody<Landing> responseBody =
                    new ResponseBody(targetLanding, "delete landing", null);
            return ResponseEntity.ok(responseBody);
        }
        catch (Exception e){
            ResponseBody responseBody =
                    new ResponseBody(null, e.getMessage(), e);
            return ResponseEntity.ok(responseBody);
        }
    }

}
