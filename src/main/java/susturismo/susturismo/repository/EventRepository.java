package susturismo.susturismo.repository;

import susturismo.susturismo.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query("select up from events up where up.status = :status")
    List<Event> findAllEventsActive(@Param("status") String status);
}
