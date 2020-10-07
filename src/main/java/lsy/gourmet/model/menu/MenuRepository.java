package lsy.gourmet.model.menu;


import lsy.gourmet.model.landing.Landing;
import lsy.gourmet.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("select h from Menu h where h.id=?1 and h.landing = ?2")
    public Menu findByIdAndLanding(Integer id, Landing landing);

    @Query("select h from Menu h where h.landing = ?1")
    public List<Menu> findAllByLanding(Landing landing);
}
