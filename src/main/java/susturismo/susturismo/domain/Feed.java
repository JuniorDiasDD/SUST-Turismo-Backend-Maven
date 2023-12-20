package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.List;
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
    @Transient
    private int count_likes;
    @Transient
    private boolean user_like;

    @Transient
    private List<CommentFeed> comments;
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

    public int getCount_likes() {
        return count_likes;
    }

    public void setCount_likes(int count_likes) {
        this.count_likes = count_likes;
    }

    public boolean isUser_like() {
        return user_like;
    }

    public void setUser_like(boolean user_like) {
        this.user_like = user_like;
    }

    public List<CommentFeed> getComments() {
        return comments;
    }

    public void setComments(List<CommentFeed> comments) {
        this.comments = comments;
    }
}
