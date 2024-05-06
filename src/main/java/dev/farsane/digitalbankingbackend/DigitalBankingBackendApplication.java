package dev.farsane.digitalbankingbackend;

import dev.farsane.digitalbankingbackend.DTOs.CustomerDTO;
import dev.farsane.digitalbankingbackend.entities.AccountOperation;
import dev.farsane.digitalbankingbackend.entities.CurrentAccount;
import dev.farsane.digitalbankingbackend.entities.Customer;
import dev.farsane.digitalbankingbackend.entities.SavingAccount;
import dev.farsane.digitalbankingbackend.enums.AccountStatus;
import dev.farsane.digitalbankingbackend.enums.OperationDate;
import dev.farsane.digitalbankingbackend.exepctions.CustomerNotFoundException;
import dev.farsane.digitalbankingbackend.repositories.AccountOperationRepository;
import dev.farsane.digitalbankingbackend.repositories.BankAccountRepository;
import dev.farsane.digitalbankingbackend.repositories.CustomerRepository;
import dev.farsane.digitalbankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingBackendApplication.class, args);
    }

    @Bean
            CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            Stream.of("Soufien", "Yassin", "Hamza").forEach( name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name.toLowerCase() + "@digitalbanking.com");
                bankAccountService.saveCustomer(customer);
            });

            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*9000, 90000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*12000, 5, customer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
        };
    }


//    @Bean
    CommandLineRunner commandLineRunner(AccountOperationRepository accountOperationRepository, BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        return args -> {
            Stream.of("John", "Jane", "Jack", "Jill").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name.toLowerCase() + "@digitalbanking.com");
                customerRepository.save(customer);
            });


            customerRepository.findAll().forEach(
                    customer -> {
                        CurrentAccount currentAccount = new CurrentAccount();
                        currentAccount.setId(UUID.randomUUID().toString());
                        currentAccount.setCustomer(customer);
                        currentAccount.setBalance(Math.random()*9000);
                        currentAccount.setStatus(AccountStatus.CREATED);
                        currentAccount.setCreatedAt(new Date());
                        currentAccount.setOverDraft(9000);
                        bankAccountRepository.save(currentAccount);


                        SavingAccount savingAccount = new SavingAccount();
                        savingAccount.setId(UUID.randomUUID().toString());
                        savingAccount.setCustomer(customer);
                        savingAccount.setBalance(Math.random()*9000);
                        savingAccount.setStatus(AccountStatus.CREATED);
                        savingAccount.setCreatedAt(new Date());
                        savingAccount.setInterestRate(9000);
                        bankAccountRepository.save(savingAccount);
                    }
            );


            bankAccountRepository.findAll().forEach(account -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setAmount(Math.random()*1000);
                    accountOperation.setBankAccount(account);
                    accountOperation.setType(Math.random() > 0.5 ? OperationDate.DEBIT :OperationDate.CREDIT);
                    accountOperation.setOperationDate(new Date());
                    accountOperationRepository.save(accountOperation);
                }
            });


        };
    }

}
