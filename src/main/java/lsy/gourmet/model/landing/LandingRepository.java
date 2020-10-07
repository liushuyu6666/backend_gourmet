package lsy.gourmet.model.landing;

import lsy.gourmet.model.head.Head;
import lsy.gourmet.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LandingRepository extends JpaRepository<Landing, Integer> {

    @Query("select h from Landing h where h.id=?1 and h.username = ?2")
    public Landing findByIdAndUsername(Integer id, User username);

    @Query("select h from Landing h where h.username = ?1")
    public List<Landing> findAllByUsername(User username);
}
