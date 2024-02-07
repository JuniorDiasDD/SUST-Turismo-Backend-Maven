package susturismo.susturismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import susturismo.susturismo.domain.Pasta;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface PastaRepository extends JpaRepository<Pasta, UUID> {
    Optional<Pasta> findByName(String name);
}
