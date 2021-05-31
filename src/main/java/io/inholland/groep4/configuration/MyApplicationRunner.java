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

import java.time.LocalDateTime;
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
        // Create a new user
        User user = new User();
        user.setUsername("john");
        user.setPassword("test");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setBirthdate("01/01/1970");
        user.setRoles(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN));
        user.setStatus(Arrays.asList(User.StatusEnum.ACTIVE));
        userService.add(user);

        // Create a new userAccount
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount.setIBAN("NL ABNA 420 69");
        userAccount.setOwner(user);
        userAccount.setAccountBalance(420.69);
        userAccount.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount.setLowerLimit(100.00);
        userAccountService.add(userAccount);

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setDateTime(OffsetDateTime.now());
        transaction.setOwner(user);
        transaction.setSender("NL ABNA 420 69");
        transaction.setReceiver("NL ABNA 69 666");
        transaction.setAmount(25.00);
        transaction.setDescription("Here's your money");
        transactionService.add(transaction);
    }
}
