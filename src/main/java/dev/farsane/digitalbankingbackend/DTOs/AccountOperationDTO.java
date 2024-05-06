package dev.farsane.digitalbankingbackend.DTOs;

import dev.farsane.digitalbankingbackend.enums.OperationDate;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationDate type;
    private String description;
}
