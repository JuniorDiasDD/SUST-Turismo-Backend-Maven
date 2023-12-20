package susturismo.susturismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import susturismo.susturismo.domain.LikeFeed;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeFeedRepository extends JpaRepository<LikeFeed, UUID> {

    @Query("select up from like_feed up where up.user_id = :user_id and up.feed_id=:feed_id")
    Optional<LikeFeed> findByUserIdAndFeedId(@Param("user_id") UUID user_id,@Param("feed_id") UUID feed_id);

    @Query("select up from like_feed up where up.feed_id=:feed_id")
    List<LikeFeed> findAllByFeedId(@Param("feed_id") UUID feed_id);

}
