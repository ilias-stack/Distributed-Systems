package ma.enset.comptecqrses.queries.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.comptecqrses.common_api.enums.AccountStatus;
import ma.enset.comptecqrses.common_api.enums.OperationType;
import ma.enset.comptecqrses.common_api.events.AccountActivatedEvent;
import ma.enset.comptecqrses.common_api.events.AccountCreatedEvent;
import ma.enset.comptecqrses.common_api.events.AccountCreditedEvent;
import ma.enset.comptecqrses.common_api.events.AccountDebitedEvent;
import ma.enset.comptecqrses.common_api.queries.GetAccountByIdQuery;
import ma.enset.comptecqrses.common_api.queries.GetAllAccountsQuery;
import ma.enset.comptecqrses.queries.entities.Account;
import ma.enset.comptecqrses.queries.entities.Operation;
import ma.enset.comptecqrses.queries.repositories.AccountRepository;
import ma.enset.comptecqrses.queries.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("**************************");
        log.info("AccountCreatedEvent received");
        log.info("**************************\n");
        accountRepository.save(new Account(
                event.getId(), event.getInitialBalance(), event.getStatus(), "MAD",null
        ));
    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("**************************");
        log.info("AccountActivatedEvent received");
        log.info("**************************\n");
        Account account = accountRepository.findById(event.getId()).orElse(null);
        assert account != null;
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("**************************");
        log.info("AccountDebitedEvent received");
        log.info("**************************\n");
        Account account = accountRepository.findById(event.getId()).orElse(null);
        assert account != null;
        account.setBalance(account.getBalance()-event.getAmount());

        operationRepository.save(new Operation(null,new Date(), event.getAmount(), OperationType.DEBIT,account));
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("**************************");
        log.info("AccountCreditedEvent received");
        log.info("**************************\n");
        Account account = accountRepository.findById(event.getId()).orElse(null);
        assert account != null;
        account.setBalance(account.getBalance()+event.getAmount());

        operationRepository.save(new Operation(null,new Date(), event.getAmount(), OperationType.CREDIT,account));
        accountRepository.save(account);
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }

    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        return accountRepository.findById(query.getId()).orElse(null);
    }
}
