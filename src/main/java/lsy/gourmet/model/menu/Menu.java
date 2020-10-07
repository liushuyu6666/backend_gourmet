package lsy.gourmet.model.menu;

import lsy.gourmet.model.landing.Landing;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private String img_url;

    private Double price;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date create_at;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modified_at;

//    private String create_by;

    @ManyToOne(fetch = FetchType.EAGER)
    private Landing landing;

    public Menu() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

//    public String getCreate_by() {
//        return create_by;
//    }
//
//    public void setCreate_by(String create_by) {
//        this.create_by = create_by;
//    }

    public Landing getLanding() {
        return landing;
    }

    public void setLanding(Landing landing) {
        this.landing = landing;
    }
}
