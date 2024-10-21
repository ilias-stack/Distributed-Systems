package ma.enset.comptecqrses.common_api.commands;

import lombok.Getter;

@Getter
public class CreditAccountCommand extends BaseCommand<String>{
    private final double amount;
    private final String currency;
    public CreditAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
