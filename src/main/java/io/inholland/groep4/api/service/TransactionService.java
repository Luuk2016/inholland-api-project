package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.Transaction;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction add(Transaction transaction) {
        // @TODO: Check if transaction doesn't already exist :)
        transactionRepository.save(transaction);

        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
