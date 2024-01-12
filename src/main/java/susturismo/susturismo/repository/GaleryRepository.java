package susturismo.susturismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import susturismo.susturismo.domain.Galery;

import java.util.List;
import java.util.UUID;

@Repository
public interface GaleryRepository extends JpaRepository<Galery, UUID> {
    @Query("select up from galery up where up.reference = :reference ORDER BY criadoEm DESC")
    List<Galery> findAllByReference(@Param("reference") UUID reference);

}
