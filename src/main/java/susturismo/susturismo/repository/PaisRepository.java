package susturismo.susturismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import susturismo.susturismo.domain.Pais;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaisRepository extends JpaRepository<Pais, UUID> {

    Optional<Pais> findByName(String name);

}
