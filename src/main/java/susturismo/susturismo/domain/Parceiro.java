package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "parceiros")
@Table(name = "parceiros")
public class Parceiro extends BaseTable{
    @Id
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "NAME", length = 64)
    private String name;

    @Column(name = "DESCRIPTION",columnDefinition="TEXT")
    private String description;

    @Basic(optional = false)
    @Column(name = "STATUS", length = 254)
    private String status;

    @Column(name = "IMAGE",columnDefinition="TEXT")
    private String image;

    @Column(name = "LINK",columnDefinition="TEXT")
    private String link;

    public Parceiro(){}

    public Parceiro( String name, String description, String image, String link) {

        this.name = name;
        this.description = description;
        this.image = image;
        this.link = link;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
