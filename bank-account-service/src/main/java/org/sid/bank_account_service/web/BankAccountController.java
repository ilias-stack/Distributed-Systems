package org.sid.bank_account_service.web;

import lombok.AllArgsConstructor;
import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BankAccountController {
    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getBankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElse(null);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO saveBankAccount(@RequestBody BankAccountRequestDTO bankAccount){
        return bankAccountService.addAccount(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount updateBankAccount(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount bankAcc = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("No account with this id "+id));
        if(bankAccount.getBalance()!=null) bankAcc.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency()!=null) bankAcc.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getType()!=null) bankAcc.setType(bankAccount.getType());
        return bankAccountRepository.save(bankAcc);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public BankAccount deleteBankAccount(@PathVariable String id){
        BankAccount bankAcc = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("No account with this id "+id));
        bankAccountRepository.deleteById(id);
        return bankAcc;
    }
}
