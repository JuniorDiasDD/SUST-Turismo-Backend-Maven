package susturismo.susturismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import susturismo.susturismo.domain.Universidade;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversidadeRepository extends JpaRepository<Universidade, UUID> {

    Optional<Universidade> findByName(String name);

}
