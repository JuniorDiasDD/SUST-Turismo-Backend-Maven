package susturismo.susturismo.web.dto;

import java.util.UUID;

public class LikeDTO implements DTO{

    private UUID id;
    private AccountDTO account;
    private UUID feed_id;

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
}
