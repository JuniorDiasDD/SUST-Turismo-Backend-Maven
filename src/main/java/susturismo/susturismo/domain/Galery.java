package susturismo.susturismo.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity(name = "galery")
@Table(name = "galery")
public class Galery extends BaseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "REFERENCE")
    private UUID reference;

    @Basic(optional = false)
    @Column(name = "IMAGE", columnDefinition = "TEXT")
    private String image;
public Galery(){

}
    public Galery(UUID reference, String image) {
        this.reference = reference;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getReference() {
        return reference;
    }

    public void setReference(UUID reference) {
        this.reference = reference;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
