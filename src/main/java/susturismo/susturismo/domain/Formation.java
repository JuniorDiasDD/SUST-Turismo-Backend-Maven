package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "formation")
@Table(name = "formation")
public class Formation extends BaseTable{
    @Id
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "TITLE", columnDefinition = "TEXT")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Basic(optional = false)
    @Column(name = "OBJECTIVE", length = 254)
    private String objective;

    @Column(name = "RESPONSIBLE", length = 254)
    private String responsible;

    @Basic(optional = false)
    @Column(name = "STATUS", length = 254)
    private String status;

    @Column(name = "LINK", columnDefinition = "TEXT")
    private String link;
    @Column(name = "IMAGE",columnDefinition = "TEXT")
    private String image;
public Formation(){}
    public Formation(String title, String description, String objective, String responsible, String link, String image, Set<Category> category) {
        this.title = title;
        this.description = description;
        this.objective = objective;
        this.responsible = responsible;
        this.link = link;
        this.image = image;
        this.category = category;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "FORMATION_CATEGORY",
            joinColumns = @JoinColumn(name = "formation_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "ID"))
    private Set<Category> category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
