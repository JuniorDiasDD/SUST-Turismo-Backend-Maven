package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "portfolio")
@Table(name = "portfolio")
public class Portfolio extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "NAME", length = 64)
    private String name;

    @Column(name = "DESCRIPTION", length = 254)
    private String description;

    @Column(name = "STATUS", length = 64)
    private String status;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "PORTFOLIO_ROLE",
            joinColumns = @JoinColumn(name = "portfolio_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "ID"))
    private Set<Role> roles;


    public Portfolio(){}
    public Portfolio(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
