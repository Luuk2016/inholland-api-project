package io.inholland.groep4.api.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.threeten.bp.OffsetDateTime;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionTest {

    private Transaction createdTransaction;

    @Test
    public void createTransactionShouldNotBeNull() {
        User createdUser = new User();
        createdUser.setUsername("test-employee1");
        createdUser.setPassword("password");
        createdUser.setFirstName("John");
        createdUser.setLastName("Doe");
        createdUser.setEmail("johndoe@example.com");
        createdUser.setBirthdate("01/01/1970");

        Transaction createdTransaction = new Transaction();
        createdTransaction.setId(15L);
        createdTransaction.setOwner(createdUser);
        createdTransaction.setReceiver("NL01INHO0000000001");
        createdTransaction.setAmount(100.00);
        createdTransaction.setDescription("Description");
        createdTransaction.setRejectionFlag("");
        createdTransaction.setDateTime(OffsetDateTime.now());

        assertNotNull(createdTransaction);
    }
}
