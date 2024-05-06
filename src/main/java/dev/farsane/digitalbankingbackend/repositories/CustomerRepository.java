package dev.farsane.digitalbankingbackend.repositories;

import dev.farsane.digitalbankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
