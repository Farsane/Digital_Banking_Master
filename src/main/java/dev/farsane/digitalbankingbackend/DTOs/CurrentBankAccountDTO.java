package dev.farsane.digitalbankingbackend.DTOs;

import dev.farsane.digitalbankingbackend.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}