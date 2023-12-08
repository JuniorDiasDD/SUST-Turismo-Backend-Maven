package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "feed")
@Table(name = "feed")
public class Feed extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "DESCRIPTION", columnDefinition="TEXT")
    private String description;
    @Column(name = "IMAGE", columnDefinition = "TEXT")
    private String image;
    @Basic(optional = false)
    @Column(name = "STATUS", length = 254)
    private String status;

    @Transient
    Account account;
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
