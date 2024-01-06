package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "noticia")
@Table(name = "noticia")
public class Noticia extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "TITLE",columnDefinition="TEXT")
    private String title;

    @Column(name = "FONTE", length = 254)
    private String fonte;

    @Column(name = "DESCRIPTION", columnDefinition="TEXT")
    private String description;

    @Column(name = "TAGS", columnDefinition="TEXT")
    private String tags;

    @Column(name = "IMAGE", columnDefinition="TEXT")
    private String image;

    @Basic(optional = false)
    @Column(name = "DATE_PUBLICACAO")
    private LocalDateTime date_publicacao;

    @Basic(optional = false)
    @Column(name = "STATUS", length = 254)
    private String status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "NOTICIA_CATEGORY",
            joinColumns = @JoinColumn(name = "noticia_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "ID"))
    private Set<Category> category;

    @Transient
    Account account;
    @Transient
    Set<Noticia> semelhantes =new HashSet<>();

    public Noticia() {
    }

    public Noticia(String title, String fonte, String description, String tags, String image, LocalDateTime date_publicacao, String status, Set<Category> category) {
        this.title = title;
        this.fonte = fonte;
        this.description = description;
        this.tags = tags;
        this.image = image;
        this.date_publicacao = date_publicacao;
        this.status = status;
        this.category = category;
    }

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

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public LocalDateTime getDate_publicacao() {
        return date_publicacao;
    }

    public void setDate_publicacao(LocalDateTime date_publicacao) {
        this.date_publicacao = date_publicacao;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Noticia> getSemelhantes() {
        return semelhantes;
    }

    public void setSemelhantes(Set<Noticia> semelhantes) {
        this.semelhantes = semelhantes;
    }
}
