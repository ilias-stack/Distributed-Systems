package ma.enset.comptecqrses.queries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.comptecqrses.common_api.enums.AccountStatus;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id
    private String id;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;

    @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;
}
