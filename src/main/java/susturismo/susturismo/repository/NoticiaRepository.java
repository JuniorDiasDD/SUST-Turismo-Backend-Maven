package susturismo.susturismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import susturismo.susturismo.domain.Noticia;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, UUID> {

    Optional<Noticia> findByTitle(String title);

    @Query("select up from noticia up where up.status = :status ORDER BY criadoEm DESC")
    List<Noticia> findAllNoticiaStatus(@Param("status") String status);

    @Query("select up from noticia up where up.status = :status ORDER BY criadoEm DESC LIMIT 3")
    List<Noticia> findAllLimit(@Param("status") String status);
}
