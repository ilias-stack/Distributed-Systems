package org.sid.bank_account_service;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository,
							CustomerRepository customerRepository){
		return args -> {
			Stream.of("Ilias","Moussa","Hajar").forEach(name -> {
				Customer customer = customerRepository.save(new Customer(null,name,null));
				for (int i = 0; i < 3; i++) {
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random()>0.5 ? AccountType.SAVING_ACCOUNT : AccountType.CURRENT_ACCOUNT)
							.balance(Math.random()*90_000 + 10_000)
							.createdAt(new Date())
							.currency("MAD")
							.owner(customer)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});


		};
	}

}
