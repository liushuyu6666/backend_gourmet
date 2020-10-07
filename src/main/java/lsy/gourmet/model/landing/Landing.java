package lsy.gourmet.model.landing;

import lsy.gourmet.model.feature.Feature;
import lsy.gourmet.model.head.Head;
import lsy.gourmet.model.menu.Menu;
import lsy.gourmet.model.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "landing")
public class Landing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date create_at;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modified_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User username;

//    @OneToMany
//    @JoinColumn(name = "landing_id")
//    private List<Feature> featureList = new ArrayList<Feature>();
//
//    @OneToMany
//    @JoinColumn(name = "landing_id")
//    private List<Head> headList = new ArrayList<Head>();
//
//    @OneToMany
//    @JoinColumn(name = "landing_id")
//    private List<Menu> menuList = new ArrayList<Menu>();

    public Landing() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

//    public List<Feature> getFeatureList() {
//        return featureList;
//    }
//
//    public void setFeatureList(List<Feature> featureList) {
//        this.featureList = featureList;
//    }
//
//    public List<Head> getHeadList() {
//        return headList;
//    }
//
//    public void setHeadList(List<Head> headList) {
//        this.headList = headList;
//    }
//
//    public List<Menu> getMenuList() {
//        return menuList;
//    }
//
//    public void setMenuList(List<Menu> menuList) {
//        this.menuList = menuList;
//    }
}
