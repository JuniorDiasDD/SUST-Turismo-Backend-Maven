package susturismo.susturismo.repository;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, UUID> {
    @Query("select up from parceiros up where up.status = :status")
    List<Parceiro> findAllParceirosActive(@Param("status") String status);
    Optional<Parceiro> findByName(String name);
}
