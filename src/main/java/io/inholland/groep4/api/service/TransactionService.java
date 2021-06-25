package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.Role;
import io.inholland.groep4.api.model.Transaction;
import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.repository.TransactionRepository;
import io.inholland.groep4.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Transaction add(Transaction transaction) throws Exception {
        UserAccount sender = userAccountRepository.findByIBAN(transaction.getSender());
        UserAccount receiver = userAccountRepository.findByIBAN(transaction.getReceiver());

        //check if the sender and receiver IBANs exist and the accounts haven't been closed
        if (sender == null || sender.getAccountStatus() == UserAccount.AccountStatusEnum.INACTIVE) {
            transaction.setRejectionFlag("Error: The sender IBAN does not exist or the account has been closed!");
            return transaction;
        } else if (receiver == null || receiver.getAccountStatus() == UserAccount.AccountStatusEnum.INACTIVE) {
            transaction.setRejectionFlag("Error: The receiver IBAN does not exist or the account has been closed!");
            return transaction;
        }

        //Savings accounts are not allowed to send transactions to accounts that don't belong to the same user. Users are not allowed to send transactions to savings accounts that don't belong to them.
        if (sender.getOwner() != receiver.getOwner() || sender.getOwner().getRoles().contains(Role.ROLE_EMPLOYEE)) {
            if (sender.getAccountType() == UserAccount.AccountTypeEnum.SAVINGS) {
                transaction.setRejectionFlag("Error: Savings accounts can only send transactions to accounts that belong to the same user!");
                return transaction;
            } else if (receiver.getAccountType() == UserAccount.AccountTypeEnum.SAVINGS) {
                transaction.setRejectionFlag("Error: Transactions are not allowed to be made to savings accounts that don't belong to the same user!");
                return transaction;
            }
        }

        //check if the sender isn't attempting an illegal transaction and doesn't have insufficient funds to complete the transaction, register this transaction to the database
        if (!(transaction.getAmount() <= 0.00)) {
            if (!((sender.getAccountBalance() - transaction.getAmount()) < sender.getLowerLimit())) {
                sender.setAccountBalance(sender.getAccountBalance() - transaction.getAmount());
                receiver.setAccountBalance(receiver.getAccountBalance() + transaction.getAmount());
                transaction.setRejectionFlag("");

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
