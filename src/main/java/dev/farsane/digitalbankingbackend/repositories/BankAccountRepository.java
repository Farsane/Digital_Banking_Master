package dev.farsane.digitalbankingbackend.repositories;

import dev.farsane.digitalbankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
