package susturismo.susturismo.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity(name = "equipa")
@Table(name = "equipa")
public class Equipa extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "NAME", length = 64)
    private String name;

    @Column(name = "INSTAGRAM", length = 254)
    private String instagram;

    @Column(name = "FACEBOOK", length = 254)
    private String facebook;

    @Column(name = "TWITTER", length = 254)
    private String twitter;

    @Column(name = "EMAIL", columnDefinition = "TEXT")
    private String email;

    @Column(name = "LOCAL", columnDefinition = "TEXT")
    private String where;

    @Column(name = "OFFICE", columnDefinition = "TEXT")
    private String office;

    @Column(name = "CV", columnDefinition = "TEXT")
    private String cv;

    @Column(name = "IMAGE", columnDefinition = "TEXT")
    private String image;

    public Equipa(){}

    public Equipa(String name,String image, String instagram, String facebook, String twitter, String email, String where, String office, String cv) {
        this.name = name;
        this.instagram = instagram;
        this.facebook = facebook;
        this.twitter = twitter;
        this.email = email;
        this.where = where;
        this.office = office;
        this.cv = cv;
        this.image=image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
