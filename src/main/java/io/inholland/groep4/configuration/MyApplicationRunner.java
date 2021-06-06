package io.inholland.groep4.configuration;

import io.inholland.groep4.api.model.Transaction;
import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.service.TransactionService;
import io.inholland.groep4.api.service.UserAccountService;
import io.inholland.groep4.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.threeten.bp.OffsetDateTime;
import java.util.Arrays;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    TransactionService transactionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create the default BANK account
        UserAccount account = new UserAccount();
        account.setIBAN("NL01INHO0000000001");
        account.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        account.setAccountBalance(500.00);
        account.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccountService.add(account, false);

        // Create a new user
        User user1 = new User();
        user1.setUsername("john");
        user1.setPassword("test");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("johndoe@example.com");
        user1.setBirthdate("01/01/1970");
        userService.add(user1, true);

        // Create a new account
        UserAccount account1 = new UserAccount();
        account1.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        account1.setOwner(user1);
        account1.setAccountBalance(500.00);
        account1.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        account1.setLowerLimit(100.00);
        userAccountService.add(account1, true);

        // Create a second user
        User user2 = new User();
        user2.setUsername("jane");
        user2.setPassword("test");
        user2.setFirstName("Jane");
        user2.setLastName("Roe");
        user2.setEmail("janeroe@example.com");
        user2.setBirthdate("01/01/1970");
        userService.add(user2, false);

        // Create a second account
        UserAccount account2 = new UserAccount();
        account2.setAccountType(UserAccount.AccountTypeEnum.SAVINGS);
        account2.setOwner(user2);
        account2.setAccountBalance(500.00);
        account2.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        account2.setLowerLimit(100.00);
        userAccountService.add(account2, true);

        Transaction transaction = new Transaction();
        transaction.setReceiver(account1.getIBAN());
        transaction.setSender(account2.getIBAN());
        transaction.setAmount(12.67);
        transaction.setDescription("Rolex");
        transaction.setDateTime(OffsetDateTime.now());
        transactionService.add(transaction);
    }
}
