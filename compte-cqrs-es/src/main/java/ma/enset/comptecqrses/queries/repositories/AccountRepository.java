package ma.enset.comptecqrses.queries.repositories;

import ma.enset.comptecqrses.queries.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
