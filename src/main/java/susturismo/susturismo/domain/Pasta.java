package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "pasta")
@Table(name = "pasta")
public class Pasta extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "NAME", length = 254)
    private String name;

    @Column(name = "LINK", columnDefinition = "TEXT")
    private String link;

    @Column(name = "IMAGE", columnDefinition = "TEXT")
    private String image;

    @Column(name = "POSITION",length = 6)
    private Integer order;

    @Transient
    Account account;
    public Pasta() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
