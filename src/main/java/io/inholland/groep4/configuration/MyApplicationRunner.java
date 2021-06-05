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
        User user = new User();
        user.setUsername("john");
        user.setPassword("test");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setBirthdate("01/01/1970");
        userService.add(user, true);

        // Create a new user
        User user2 = new User();
        user2.setUsername("swagtron");
        user2.setPassword("test");
        user2.setFirstName("Dave");
        user2.setLastName("Smit");
        user2.setEmail("swagtron@example.com");
        user2.setBirthdate("01/01/1970");
        userService.add(user2, false);

        // Create a new user
        User user3 = new User();
        user3.setUsername("zenma1zx");
        user3.setPassword("test");
        user3.setFirstName("Aaron");
        user3.setLastName("Ng");
        user3.setEmail("zenma@example.com");
        user3.setBirthdate("01/01/1970");
        userService.add(user3, false);

        // Create a new account
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount.setOwner(user);
        userAccount.setAccountBalance(500.00);
        userAccount.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount.setLowerLimit(100.00);
        userAccountService.add(userAccount, true);

        // Create a new account
        UserAccount userAccount2 = new UserAccount();
        userAccount2.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount2.setOwner(user2);
        userAccount2.setAccountBalance(500.00);
        userAccount2.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount2.setLowerLimit(100.00);
        userAccountService.add(userAccount2, true);

        // Create a new account
        UserAccount userAccount3 = new UserAccount();
        userAccount3.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount3.setOwner(user3);
        userAccount3.setAccountBalance(500.00);
        userAccount3.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount3.setLowerLimit(100.00);
        userAccountService.add(userAccount3, true);

        // Create a new account
        UserAccount userAccount4 = new UserAccount();
        userAccount4.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount4.setOwner(user3);
        userAccount4.setAccountBalance(500.00);
        userAccount4.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount4.setLowerLimit(100.00);
        userAccountService.add(userAccount4, true);

//        Transaction transaction = new Transaction();
//        transaction.setReceiver(userAccount3.getIBAN());
//        transaction.setSender(userAccount2.getIBAN());
//        transaction.setAmount(12.67);
//        transaction.setDescription("Rolex");
//        transactionService.add(transaction);
    }
}
