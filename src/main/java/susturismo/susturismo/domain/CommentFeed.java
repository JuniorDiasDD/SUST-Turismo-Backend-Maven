package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "comment_feed")
@Table(name = "comment_feed")
public class CommentFeed extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "USER_ID")
    private UUID user_id;

    @Basic(optional = false)
    @Column(name = "FEED_ID")
    private UUID feed_id;

    @Basic(optional = false)
    @Column(name = "COMMENT", columnDefinition="TEXT")
    private String comment;

    @Transient
    Account account;
    public CommentFeed(){}

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
