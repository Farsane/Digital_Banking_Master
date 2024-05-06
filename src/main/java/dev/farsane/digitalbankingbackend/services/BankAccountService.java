package dev.farsane.digitalbankingbackend.services;

import dev.farsane.digitalbankingbackend.DTOs.*;
import dev.farsane.digitalbankingbackend.exepctions.BalanceNotSufficientException;
import dev.farsane.digitalbankingbackend.exepctions.BankAccountNotFoundException;
import dev.farsane.digitalbankingbackend.exepctions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    List<CustomerDTO> listCustomers();
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialDeposit, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialDeposit, double interestRate, Long customerId) throws CustomerNotFoundException;
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String fromAccountId, String toAccountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    AccountHistoryDTO getAccountOperationById(String accountId, int page, int size) throws BankAccountNotFoundException;
}
