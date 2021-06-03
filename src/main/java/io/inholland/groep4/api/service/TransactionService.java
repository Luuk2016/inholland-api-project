package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.Transaction;
import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.repository.TransactionRepository;
import io.inholland.groep4.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Transaction add(Transaction transaction) {
        UserAccount sender = userAccountRepository.findByIBAN(transaction.getSender());
        UserAccount receiver = userAccountRepository.findByIBAN(transaction.getReceiver());

        //if the sender isn't attempting an illegal transaction and doesn't have insufficient funds to complete the transaction
        if (!(transaction.getAmount() <= 0.00)) {
            if (!((sender.getAccountBalance() - transaction.getAmount()) < sender.getLowerLimit())) {
                sender.setAccountBalance(sender.getAccountBalance() - transaction.getAmount());
                receiver.setAccountBalance(receiver.getAccountBalance() + transaction.getAmount());

                transactionRepository.save(transaction);
            }
            return transaction;
        }

        return null;
    }

    public List<Transaction> getAllTransactions() { return transactionRepository.findAll(); }

    public Transaction getTransactionById(Long id) { return transactionRepository.getTransactionById(id); }

    public List<Transaction> getAllUserTransactions(User user) { return transactionRepository.getTransactionByOwner(user); }

    public boolean checkIfTransactionBelongsToOwner(User user, Long id) { return transactionRepository.existsByOwnerAndId(user, id); }
}
