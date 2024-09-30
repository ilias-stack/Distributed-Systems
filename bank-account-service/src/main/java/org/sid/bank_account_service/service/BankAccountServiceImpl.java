package org.sid.bank_account_service.service;

import lombok.AllArgsConstructor;
import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.BankAccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private BankAccountRepository bankAccountRepository;
    private BankAccountMapper bankAccountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .balance(bankAccountDTO.getBalance())
                .build();
        final BankAccount saved = bankAccountRepository.save(bankAccount);

        return bankAccountMapper.fromBankAccount(saved);
    }
}
