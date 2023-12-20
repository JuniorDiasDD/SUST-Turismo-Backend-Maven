package susturismo.susturismo.web.dto;
import java.time.LocalDateTime;
import java.util.UUID;

public class CommentFeedDTO {
    private UUID id;
    private AccountDTO account;
    private UUID feed_id;
    private String comment;
    private LocalDateTime data;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
