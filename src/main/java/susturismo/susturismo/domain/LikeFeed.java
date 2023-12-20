package susturismo.susturismo.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity(name = "like_feed")
@Table(name = "like_feed")
public class LikeFeed extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "USER_ID", length = 64)
    private UUID user_id;

    @Basic(optional = false)
    @Column(name = "FEED_ID", length = 64)
    private UUID feed_id;

    public LikeFeed(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(UUID feed_id) {
        this.feed_id = feed_id;
    }
}
