package susturismo.susturismo.repository;

import susturismo.susturismo.domain.Feed;
import susturismo.susturismo.domain.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedRepository extends JpaRepository<Feed, UUID> {
    @Query("select up from feed up where up.status = :status ORDER BY criadoEm DESC")
    List<Feed> findAllFeedStatus(@Param("status") String status);
}
