package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.Transaction;
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

    List<Transaction> transactions;

    Transaction transaction = new Transaction();

    @Test
    public void gettingAllTransactionsShouldGiveListOfUsers() {
        transactions = transactionService.getAllTransactions();

        assertThat(transactions).isNotEmpty();
        assertThat(transactions.get(0).getId()).isEqualTo(10);
        assertThat(transactions.get(0).getAmount()).isEqualTo(9.95);
        assertThat(transactions.get(0).getDateTime()).isEqualTo(null);
        assertThat(transactions.get(0).getDescription()).isEqualTo("TEST-TRANSACTION");
        assertThat(transactions.get(0).getReceiver()).isEqualTo("USER_ACCOUNT_2_IBAN");
        assertThat(transactions.get(0).getRejectionFlag()).isEqualTo("");
        assertThat(transactions.get(0).getSender()).isEqualTo("USER_ACCOUNT_1_IBAN");
        assertThat(transactions.get(0).getOwner()).isEqualTo(null);
    }

}
