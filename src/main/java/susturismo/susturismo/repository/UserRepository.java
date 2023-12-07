package susturismo.susturismo.repository;

import susturismo.susturismo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
   UserDetails findByLogin(String login);

  @Query("select up from users up where up.login = :username")
  Optional<User> findByUsername(@Param("username") String login);
}
