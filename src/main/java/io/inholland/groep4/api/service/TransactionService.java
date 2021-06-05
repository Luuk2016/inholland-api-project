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

        //check if the sender and receiver IBANs exist
        if (sender == null) {
            transaction.setRejectionFlag("Error: The sender IBAN does not exist!");
            return transaction;
        } else if (receiver == null) {
            transaction.setRejectionFlag("Error: The receiver IBAN does not exist!");
            return transaction;
        }

        //Users are not allowed to send transactions to savings accounts that don't belong to them. Savings accounts are not allowed to send transactions to accounts that don't belong to the same user
        if (sender.getAccountType() == UserAccount.AccountTypeEnum.CURRENT) {
            if (receiver.getAccountType() == UserAccount.AccountTypeEnum.SAVINGS) {
                transaction.setRejectionFlag("Error: Transactions are not allowed to be made to savings accounts that don't belong to the same user!");
                return transaction;
            }
        } else {
            if (sender.getOwner() != receiver.getOwner()) {
                transaction.setRejectionFlag("Error: Savings accounts can only send transactions to current accounts that belong to the same user!");
                return transaction;
            }
        }

        //if the sender isn't attempting an illegal transaction and doesn't have insufficient funds to complete the transaction, register this transaction to the database
        if (!(transaction.getAmount() <= 0.00)) {
            if (!((sender.getAccountBalance() - transaction.getAmount()) < sender.getLowerLimit())) {
                sender.setAccountBalance(sender.getAccountBalance() - transaction.getAmount());
                receiver.setAccountBalance(receiver.getAccountBalance() + transaction.getAmount());

                transactionRepository.save(transaction);
            } else {
                transaction.setRejectionFlag("Error: Insufficient funds!");
            }
            return transaction;
        } else {
            transaction.setRejectionFlag("Zero or negative amounts are not allowed!");
        }
        return transaction;
    }

    public List<Transaction> getAllTransactions() { return transactionRepository.findAll(); }

    public Transaction getTransactionById(Long id) { return transactionRepository.getTransactionById(id); }

    public List<Transaction> getAllUserTransactions(User user) { return transactionRepository.getTransactionByOwner(user); }

    public boolean checkIfTransactionBelongsToOwner(User user, Long id) { return transactionRepository.existsByOwnerAndId(user, id); }
}
