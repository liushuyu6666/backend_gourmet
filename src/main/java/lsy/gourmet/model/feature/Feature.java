package lsy.gourmet.model.feature;

import lsy.gourmet.model.landing.Landing;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feature")
public class Feature{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private String icon_url;

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

    public Feature() {
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

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
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
