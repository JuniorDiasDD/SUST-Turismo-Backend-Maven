package susturismo.susturismo.web.dto;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class NoticiaDTO {

    private UUID id;
    private String title;
    private String description;
    private String status;
    private String fonte;
    private String tags;
    private String image;
    private LocalDateTime date_publicacao;
    private AccountDTO account;
    Set<CategoryDTO> categories;
    Set<NoticiaDTO> semelhantes;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
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

    public Set<NoticiaDTO> getSemelhantes() {
        return semelhantes;
    }

    public void setSemelhantes(Set<NoticiaDTO> semelhantes) {
        this.semelhantes = semelhantes;
    }
}
