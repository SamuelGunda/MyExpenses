package kasv.gunda.myexpense.repositories;

import kasv.gunda.myexpense.models.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
