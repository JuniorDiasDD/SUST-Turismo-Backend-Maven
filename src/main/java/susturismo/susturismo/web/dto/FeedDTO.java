package susturismo.susturismo.web.dto;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class FeedDTO {

    private UUID id;
    private String image;
    private String description;
    private String status;
    private AccountDTO account;
    private LocalDateTime data;
    private int likes;
    private boolean user_like;

    Set<CommentFeedDTO> comments;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isUser_like() {
        return user_like;
    }

    public void setUser_like(boolean user_like) {
        this.user_like = user_like;
    }

    public Set<CommentFeedDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentFeedDTO> comments) {
        this.comments = comments;
    }
}
