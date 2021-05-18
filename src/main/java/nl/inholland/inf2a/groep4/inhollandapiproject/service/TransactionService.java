package nl.inholland.inf2a.groep4.inhollandapiproject.service;

import nl.inholland.inf2a.groep4.inhollandapiproject.model.Transaction;
import nl.inholland.inf2a.groep4.inhollandapiproject.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Add a new transaction
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
