package susturismo.susturismo.repository;

import susturismo.susturismo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import susturismo.susturismo.domain.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByLogin(String login);

    @Query("select up from account up where up.auth = :id")
    Optional<Account> findAccountByAuth(@Param("id") UUID id);

    @Query(value="select * from account up where up.google_id = :googleId",nativeQuery = true)
    Optional<Account> findByGoogleId(@Param("googleId") String googleId);

}
