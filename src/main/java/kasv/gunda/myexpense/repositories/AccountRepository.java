package kasv.gunda.myexpense.repositories;

import kasv.gunda.myexpense.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByIdAndUsersEmail(UUID uuid, String userEmail);
}
