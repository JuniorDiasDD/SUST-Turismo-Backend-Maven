package susturismo.susturismo.repository;

import susturismo.susturismo.domain.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormationRepository extends JpaRepository<Formation, UUID> {
    @Query("select up from formation up where up.status = :status order by criadoEm DESC")
    List<Formation> findAllFormationStatus(@Param("status") String status);
    Optional<Formation> findByTitle(String title);

}
