package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "events")
@Table(name = "events")
public class Event extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "TITLE", columnDefinition = "TEXT")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "ORGANIZER", columnDefinition = "TEXT")
    private String organizer;

    @Basic(optional = false)
    @Column(name = "DATE_INIT")
    private LocalDateTime date_init;
    @Basic(optional = false)
    @Column(name = "DATE_FINISH")
    private LocalDateTime date_finish;

    @Column(name = "HOUR_INIT", length = 254)
    private String hour_init;

    @Column(name = "HOUR_FINISH", length = 254)
    private String hour_finish;

    @Basic(optional = false)
    @Column(name = "LOCAL", length = 254)
    private String local;

    @Basic(optional = false)
    @Column(name = "STATUS", length = 254)
    private String status;

    @Column(name = "TAGS", columnDefinition = "TEXT")
    private String tags;

    @Column(name = "IMAGE", columnDefinition = "TEXT")
    private String image;
    @Column(name = "PRICE")
    private Float price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "EVENT_CATEGORY",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "ID"))
    private Set<Category> category;

    @Transient
    Account account;
    @Transient
    Set<Event>semelhantes=new HashSet<>();
    @Transient
    Set<String>galery=new HashSet<>();
    public Event(){

    }

    public Event(String title, String description, String organizer, LocalDateTime date_init, LocalDateTime date_finish, String hour_init, String hour_finish, String local, String status, String tags, String image, Float price, Set<Category> category) {
        this.title = title;
        this.description = description;
        this.organizer = organizer;
        this.date_init = date_init;
        this.date_finish = date_finish;
        this.hour_init = hour_init;
        this.hour_finish = hour_finish;
        this.local = local;
        this.status = status;
        this.tags = tags;
        this.image = image;
        this.price = price;
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

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<Event> getSemelhantes() {
        return semelhantes;
    }

    public void setSemelhantes(Set<Event> semelhantes) {
        this.semelhantes = semelhantes;
    }

    public Set<String> getGalery() {
        return galery;
    }

    public void setGalery(Set<String> galery) {
        this.galery = galery;
    }
}
