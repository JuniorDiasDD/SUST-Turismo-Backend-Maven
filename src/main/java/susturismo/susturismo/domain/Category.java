package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "category")
@Table(name = "category")
public class Category extends BaseTable{
    @Id
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "NAME", length = 64)
    private String name;

    @Column(name = "DESCRIPTION", length = 254)
    private String description;

    @Column(name = "STATUS", length = 64)
    private String status;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Event> events;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Formation> formation;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Feed> feeds;

    public Category(){}

    public Category( String name, String description) {

        this.name = name;
        this.description = description;
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

    public Set<Event> getEvents() {
        return events;
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

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Formation> getFormation() {
        return formation;
    }

    public void setFormation(Set<Formation> formation) {
        this.formation = formation;
    }

    public Set<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(Set<Feed> feeds) {
        this.feeds = feeds;
    }
}
