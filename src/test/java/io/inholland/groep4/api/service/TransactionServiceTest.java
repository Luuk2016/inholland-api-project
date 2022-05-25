package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.Transaction;
import io.inholland.groep4.api.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserAccountService userAccountService;

    List<Transaction> transactions;

    // Fetch two test employee accounts
    UserAccount testEmployeeAccount1, testEmployeeAccount2;

    Transaction transaction = new Transaction();

    @Test
    public void gettingAllTransactionsShouldGiveListOfUsers() {
        transactions = transactionService.getAllTransactions();

        assertThat(transactions).isNotEmpty();
        assertThat(transactions.get(0).getAmount()).isEqualTo(9.95);
        assertThat(transactions.get(0).getDateTime()).isEqualTo(null);
        assertThat(transactions.get(0).getDescription()).isEqualTo("TEST-TRANSACTION");
        assertThat(transactions.get(0).getReceiver()).isEqualTo("USER_ACCOUNT_2_IBAN");
        assertThat(transactions.get(0).getRejectionFlag()).isEqualTo("");
        assertThat(transactions.get(0).getSender()).isEqualTo("USER_ACCOUNT_1_IBAN");
        assertThat(transactions.get(0).getOwner()).isEqualTo(null);
    }

    @Test
    public void creatingANewTransactionSuccessfullyShouldGiveObject() {
        testEmployeeAccount1 = userAccountService.getAccountById(6L);
        testEmployeeAccount2 = userAccountService.getAccountById(7L);

        transaction.setSender(testEmployeeAccount1.getIBAN());
        transaction.setReceiver(testEmployeeAccount2.getIBAN());
        transaction.setAmount(9.95);
        transaction.setDescription("TEST-TRANSACTION");
        Transaction response = transactionService.add(transaction);

        assertEquals(response.getSender(), transaction.getSender());
        assertEquals(response.getReceiver(), transaction.getReceiver());
        assertEquals(response.getAmount(), transaction.getAmount());
        assertEquals(response.getDescription(), transaction.getDescription());
    }

    @Test
    public void creatingANewTransactionWithInsufficientFundsShouldGiveObjectWithRejectionFlag() {
        testEmployeeAccount1 = userAccountService.getAccountById(6L);
        testEmployeeAccount2 = userAccountService.getAccountById(7L);

        transaction.setSender(testEmployeeAccount1.getIBAN());
        transaction.setReceiver(testEmployeeAccount2.getIBAN());
        transaction.setAmount(999.99);
        transaction.setDescription("TEST-TRANSACTION");
        Transaction response = transactionService.add(transaction);

        assertEquals(response.getSender(), transaction.getSender());
        assertEquals(response.getRejectionFlag(), "Error: Insufficient funds!");
    }

    @Test
    public void creatingANewTransactionWithInactiveSenderAccountShouldGiveObjectWithRejectionFlag() {
        testEmployeeAccount1 = userAccountService.getAccountById(10L);
        testEmployeeAccount2 = userAccountService.getAccountById(7L);

        transaction.setSender(testEmployeeAccount1.getIBAN());
        transaction.setReceiver(testEmployeeAccount2.getIBAN());
        transaction.setAmount(9.95);
        transaction.setDescription("TEST-TRANSACTION");
        Transaction response = transactionService.add(transaction);

        assertEquals(response.getSender(), transaction.getSender());
        assertEquals(response.getRejectionFlag(), "Error: The sender IBAN does not exist or the account has been closed!");
    }

    @Test
    public void creatingANewTransactionWithInactiveReceiverAccountShouldGiveObjectWithRejectionFlag() {
        testEmployeeAccount1 = userAccountService.getAccountById(6L);
        testEmployeeAccount2 = userAccountService.getAccountById(11L);

        transaction.setSender(testEmployeeAccount1.getIBAN());
        transaction.setReceiver(testEmployeeAccount2.getIBAN());
        transaction.setAmount(9.95);
        transaction.setDescription("TEST-TRANSACTION");
        Transaction response = transactionService.add(transaction);

        assertEquals(response.getSender(), transaction.getSender());
        assertEquals(response.getRejectionFlag(), "Error: The receiver IBAN does not exist or the account has been closed!");
    }

    @Test
    public void creatingANewTransactionFromSavingsToRemoteCurrentAccountShouldGiveObjectWithRejectionFlag() {
        testEmployeeAccount1 = userAccountService.getAccountById(6L);
        testEmployeeAccount2 = userAccountService.getAccountById(12L);

        transaction.setSender(testEmployeeAccount1.getIBAN());
        transaction.setReceiver(testEmployeeAccount2.getIBAN());
        transaction.setAmount(9.95);
        transaction.setDescription("TEST-TRANSACTION");
        Transaction response = transactionService.add(transaction);

        assertEquals(response.getSender(), transaction.getSender());
        assertEquals(response.getRejectionFlag(), "Error: Transactions are not allowed to be made to savings accounts that don't belong to the same user!");
    }

    @Test
    public void creatingANewTransactionFromCurrentToRemoteSavingsAccountShouldGiveObjectWithRejectionFlag() {
        testEmployeeAccount1 = userAccountService.getAccountById(13L);
        testEmployeeAccount2 = userAccountService.getAccountById(7L);

        transaction.setSender(testEmployeeAccount1.getIBAN());
        transaction.setReceiver(testEmployeeAccount2.getIBAN());
        transaction.setAmount(9.95);
        transaction.setDescription("TEST-TRANSACTION");
        Transaction response = transactionService.add(transaction);

        assertEquals(response.getSender(), transaction.getSender());
        assertEquals(response.getRejectionFlag(), "Error: Savings accounts can only send transactions to accounts that belong to the same user!");
    }
}
