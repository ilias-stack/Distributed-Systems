package ma.enset.comptecqrses.queries.repositories;

import ma.enset.comptecqrses.queries.entities.Account;
import ma.enset.comptecqrses.queries.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
