package dev.farsane.digitalbankingbackend.entities;

import dev.farsane.digitalbankingbackend.enums.OperationDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationDate type;
    @ManyToOne
    BankAccount bankAccount;
    private String description;
}
