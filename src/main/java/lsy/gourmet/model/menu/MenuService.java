package lsy.gourmet.model.menu;

import lsy.gourmet.model.landing.Landing;
import lsy.gourmet.model.landing.LandingRepository;
import lsy.gourmet.model.user.User;
import lsy.gourmet.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    LandingRepository landingRepository;

    @Autowired
    UserRepository userRepository;

    public Menu createMenu(Menu menuBody, Integer landingId, String username)throws Exception{

        User targetUser = userRepository.findUserByUsername(username);
        Landing targetLanding = landingRepository.findByIdAndUsername(landingId, targetUser);
        if(targetLanding != null){
            Menu newMenu = new Menu();
            newMenu.setDescription(menuBody.getDescription());
            newMenu.setImg_url(menuBody.getImg_url());
            newMenu.setLanding(targetLanding);
            newMenu.setPrice(menuBody.getPrice());
            newMenu.setStatus(menuBody.getStatus());
            newMenu.setTitle(menuBody.getTitle());

            return menuRepository.save(newMenu);
        }
        else{
            throw new Exception("no such landing, so can't create the menu");
        }

    }

    public Menu retrieveMenu(Integer id, Integer landingId, String username) throws Exception{

        User targetUser = userRepository.findUserByUsername(username);
        Landing targetLanding = landingRepository.findByIdAndUsername(landingId, targetUser);
        if(targetLanding != null){
            Menu foundMenu = menuRepository.findByIdAndLanding(id, targetLanding);

            return foundMenu;
        }
        else{
            throw new Exception("no such landing, so can't retrieve menu");
        }

    }

    public List<Menu> listMenu(Integer landingId, String username) throws Exception{

        User targetUser = userRepository.findUserByUsername(username);
        Landing targetLanding = landingRepository.findByIdAndUsername(landingId, targetUser);
        if(targetLanding != null){
            List<Menu> menuList = menuRepository.findAllByLanding(targetLanding);

            return menuList;
        }
        else {
            throw new Exception("no such landing, so can't list menus");
        }

    }

    public Menu updateMenu(Integer id, Menu menuBody, Integer landingId, String username) throws Exception{

        User targetUser = userRepository.findUserByUsername(username);
        Landing targetLanding = landingRepository.findByIdAndUsername(landingId, targetUser);
        if(targetLanding != null){
            Menu updatingMenu = menuRepository.findByIdAndLanding(id, targetLanding);
            if(updatingMenu != null){
                updatingMenu.setDescription(menuBody.getDescription());
                updatingMenu.setImg_url(menuBody.getImg_url());
                updatingMenu.setLanding(targetLanding);
                updatingMenu.setPrice(menuBody.getPrice());
                updatingMenu.setStatus(menuBody.getStatus());
                updatingMenu.setTitle(menuBody.getTitle());

                return menuRepository.save(updatingMenu);
            }
            else{
                throw new Exception("no such menu, so can't update");
            }
        }
        else{
            throw new Exception("no such landing, so can't update menu");
        }
    }

    public Menu deleteMenu(Integer id, Integer landingId, String username) throws Exception{

        User targetUser = userRepository.findUserByUsername(username);
        Landing targetLanding = landingRepository.findByIdAndUsername(landingId, targetUser);
        if(targetLanding != null){
            Menu deletedMenu = menuRepository.findByIdAndLanding(id, targetLanding);
            System.out.println(deletedMenu);
            if(deletedMenu != null){
                System.out.println(1);
                Menu deletingMenu = new Menu();
                deletingMenu.setDescription(deletedMenu.getDescription());
                deletingMenu.setImg_url(deletedMenu.getImg_url());
                deletingMenu.setLanding(targetLanding);
                deletingMenu.setPrice(deletedMenu.getPrice());
                deletingMenu.setStatus(deletedMenu.getStatus());
                deletingMenu.setTitle(deletedMenu.getTitle());
                deletingMenu.setCreate_at(deletedMenu.getCreate_at());
                deletingMenu.setId(deletedMenu.getId());
                System.out.println(deletedMenu.getId());
                menuRepository.deleteById(id);
                System.out.println(3);
                return deletingMenu;
            }
            else{
                throw new Exception("no such menu, can't delete");
            }
        }
        else{
            throw new Exception("no such landing, so can't delete menu");
        }
    }

}
