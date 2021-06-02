package io.inholland.groep4.configuration;

import io.inholland.groep4.api.model.Role;
import io.inholland.groep4.api.model.Transaction;
import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.service.TransactionService;
import io.inholland.groep4.api.service.UserAccountService;
import io.inholland.groep4.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
        account.setIBAN("NL01 INHO 0000 0000 01");
        account.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        account.setAccountBalance(420.69);
        account.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        account.setLowerLimit(100.00);
        userAccountService.add(account);

        // Create a new user
        User user = new User();
        user.setUsername("john");
        user.setPassword("test");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setBirthdate("01/01/1970");
        user.setRoles(Arrays.asList(Role.ROLE_USER, Role.ROLE_EMPLOYEE));
        user.setStatus(Arrays.asList(User.StatusEnum.ACTIVE));
        userService.add(user);

        // Create a new userAccount
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount.setIBAN("NL01 INHO 0420 6969 00");
        userAccount.setOwner(user);
        userAccount.setAccountBalance(420.69);
        userAccount.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount.setLowerLimit(100.00);
        userAccountService.add(userAccount);

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setDateTime(OffsetDateTime.now());
        transaction.setOwner(user);
        transaction.setSender("NL01 INHO 0420 6969 00");
        transaction.setReceiver("NL01 INHO 0420 6969 11");
        transaction.setAmount(25.00);
        transaction.setDescription("Here's your money");
        transactionService.add(transaction);
    }
}
