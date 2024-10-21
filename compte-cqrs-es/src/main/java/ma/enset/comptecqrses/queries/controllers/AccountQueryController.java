package ma.enset.comptecqrses.queries.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.comptecqrses.common_api.queries.GetAccountByIdQuery;
import ma.enset.comptecqrses.common_api.queries.GetAllAccountsQuery;
import ma.enset.comptecqrses.queries.entities.Account;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/accounts")
@AllArgsConstructor
@Slf4j
public class AccountQueryController {
    private QueryGateway queryGateway;

    @GetMapping
    public List<Account> accountList(){
        return queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable String accountId){
        return queryGateway.query(new GetAccountByIdQuery(accountId), ResponseTypes.instanceOf(Account.class)).join();
    }
}
