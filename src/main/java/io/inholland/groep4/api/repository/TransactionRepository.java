package io.inholland.groep4.api.repository;

import io.inholland.groep4.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction getTransactionById(Long id);
}
