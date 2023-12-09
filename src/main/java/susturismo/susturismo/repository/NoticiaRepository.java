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

    @Query(value="select * from noticia as up JOIN noticia_category as nc on up.id=nc.noticia_id where up.id<>:id and up.status = :status and nc.category_id=:category ORDER BY up.criado_em DESC LIMIT 6",nativeQuery = true)
    List<Noticia> findAllSemelhante(@Param("status") String status,@Param("category") UUID category,@Param("id") UUID id);
}
