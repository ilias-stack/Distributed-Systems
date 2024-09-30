package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        return BankAccountResponseDTO.builder()
                .id(bankAccount.getId())
                .createdAt(bankAccount.getCreatedAt())
                .type(bankAccount.getType())
                .currency(bankAccount.getCurrency())
                .balance(bankAccount.getBalance())
                .build();
    }
}
