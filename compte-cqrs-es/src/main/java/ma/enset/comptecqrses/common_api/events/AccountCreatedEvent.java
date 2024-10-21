package ma.enset.comptecqrses.common_api.events;

import lombok.Getter;
import ma.enset.comptecqrses.common_api.enums.AccountStatus;

@Getter
public class AccountCreatedEvent extends BaseEvent<String>{
    private final double initialBalance;
    private final String currency;
    private final AccountStatus status;
    public AccountCreatedEvent(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.status = AccountStatus.CREATED;
    }
}
