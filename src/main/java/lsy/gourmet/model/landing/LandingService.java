package lsy.gourmet.model.landing;

import lsy.gourmet.model.menu.Menu;
import lsy.gourmet.model.menu.MenuRepository;
import lsy.gourmet.model.user.User;
import lsy.gourmet.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandingService {

    @Autowired
    LandingRepository landingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    public Landing createLanding(Landing newLandingBody, String username){

        User joinUser = userRepository.findUserByUsername(username);
        Landing createdLanding = new Landing();
        createdLanding.setTitle(newLandingBody.getTitle());
        createdLanding.setStatus(newLandingBody.getStatus());
        createdLanding.setUsername(joinUser);

        return landingRepository.save(createdLanding);
    }

    public Landing retrieveLanding(Integer id, String username){
        User targetUser = userRepository.findUserByUsername(username);
        return landingRepository.findByIdAndUsername(id, targetUser);
    }

    public List<Landing> listLanding(String username){
        User targetUser = userRepository.findUserByUsername(username);
        return landingRepository.findAllByUsername(targetUser);
    }

    public Landing updateLanding(Integer id, Landing landingBody, String username) throws Exception{
        User targetUser = userRepository.findUserByUsername(username);
        Landing updatedLanding = landingRepository.findByIdAndUsername(id, targetUser);
        if(updatedLanding != null){
            updatedLanding.setTitle(landingBody.getTitle());
            updatedLanding.setStatus(landingBody.getStatus());
            updatedLanding.setUsername(targetUser);

            return landingRepository.save(updatedLanding);
        }
        else{
            throw new Exception("no such landing, can't update");
        }
    }

    public Landing deleteLanding(Integer id, String username) throws Exception{
        User targetUser = userRepository.findUserByUsername(username);
        Landing deletedLanding = landingRepository.findByIdAndUsername(id, targetUser);
        if(deletedLanding != null){
            /** remove all menus*/
            List<Menu> menuList = menuRepository.findAllByLanding(deletedLanding);
            for(int i=0; i<menuList.size(); i++){
                Integer menuId = menuList.get(i).getId();
                menuRepository.deleteById(menuId);
            }

            Landing deleteLanding = new Landing();
            deleteLanding.setTitle(deletedLanding.getTitle());
            deleteLanding.setStatus(deletedLanding.getStatus());
            deleteLanding.setUsername(deletedLanding.getUsername());
            deleteLanding.setId(deletedLanding.getId());
            deleteLanding.setCreate_at(deletedLanding.getCreate_at());
            landingRepository.deleteById(id);
            return deleteLanding;
        }
        else{
            throw new Exception("no such landing, can't delete");
        }
    }

}
