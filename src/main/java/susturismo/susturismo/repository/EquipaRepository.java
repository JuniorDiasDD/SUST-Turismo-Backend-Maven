package susturismo.susturismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import susturismo.susturismo.domain.Equipa;
import java.util.UUID;

@Repository
public interface EquipaRepository extends JpaRepository<Equipa, UUID> {

}
