package susturismo.susturismo.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity(name = "parceiros")
@Table(name = "parceiros")
@EqualsAndHashCode(of="id")
public class Parceiro extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "NAME", length = 64)
    private String name;

    @Column(name = "DESCRIPTION", length = 254)
    private String description;

    @Basic(optional = false)
    @Column(name = "STATUS", length = 254)
    private String status;

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
}
