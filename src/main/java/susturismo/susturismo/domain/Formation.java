package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "formation")
@Table(name = "formation")
public class Formation extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "TITLE", length = 64)
    private String title;

    @Column(name = "DESCRIPTION", length = 254)
    private String description;

    @Basic(optional = false)
    @Column(name = "OBJECTIVE", length = 254)
    private String objective;

    @Column(name = "RESPONSIBLE", length = 254)
    private String responsible;

    @Basic(optional = false)
    @Column(name = "STATUS", length = 254)
    private String status;

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
}
