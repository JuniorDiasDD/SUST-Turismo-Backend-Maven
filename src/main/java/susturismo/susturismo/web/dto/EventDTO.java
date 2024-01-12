package susturismo.susturismo.web.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class EventDTO {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime date_init;
    private LocalDateTime date_finish;
    private String hour_init;
    private String hour_finish;
    private String local;
    private String status;
    private String tags;
    private String image;
    private String organizer;
    private Float price;
    private AccountDTO account;
    Set<CategoryDTO> categories;
    Set<EventDTO> semelhantes;
    Set<String> galery;

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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDateTime getDate_init() {
        return date_init;
    }

    public void setDate_init(LocalDateTime date_init) {
        this.date_init = date_init;
    }

    public LocalDateTime getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(LocalDateTime date_finish) {
        this.date_finish = date_finish;
    }

    public String getHour_init() {
        return hour_init;
    }

    public void setHour_init(String hour_init) {
        this.hour_init = hour_init;
    }

    public String getHour_finish() {
        return hour_finish;
    }

    public void setHour_finish(String hour_finish) {
        this.hour_finish = hour_finish;
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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<EventDTO> getSemelhantes() {
        return semelhantes;
    }

    public void setSemelhantes(Set<EventDTO> semelhantes) {
        this.semelhantes = semelhantes;
    }

    public Set<String> getGalery() {
        return galery;
    }

    public void setGalery(Set<String> galery) {
        this.galery = galery;
    }
}
