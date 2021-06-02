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


        // Create a new account
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount.setOwner(user);
        userAccount.setAccountBalance(500.00);
        userAccount.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount.setLowerLimit(100.00);
        userAccountService.add(userAccount, true);
    }
}
