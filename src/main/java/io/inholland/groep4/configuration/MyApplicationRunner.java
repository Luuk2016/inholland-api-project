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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        user.setRoles(Arrays.asList(Role.ROLE_USER, Role.ROLE_EMPLOYEE));
        user.setStatus(Arrays.asList(User.StatusEnum.ACTIVE));
        userService.add(user);

        // Create new userAccounts
        UserAccount userAccountJohnCurrent = new UserAccount();
        userAccountJohnCurrent.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccountJohnCurrent.setIBAN("NL ABNA 420 69");
        userAccountJohnCurrent.setOwner(user);
        userAccountJohnCurrent.setAccountBalance(420.69);
        userAccountJohnCurrent.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccountJohnCurrent.setLowerLimit(100.00);
        userAccountService.add(userAccountJohnCurrent);

        UserAccount userAccountJohnSavings = new UserAccount();
        userAccountJohnSavings.setAccountType(UserAccount.AccountTypeEnum.SAVINGS);
        userAccountJohnSavings.setIBAN("NL ABNA 420 70");
        userAccountJohnSavings.setOwner(user);
        userAccountJohnSavings.accountBalance(1337.00);
        userAccountJohnSavings.accountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccountJohnSavings.setLowerLimit(100.00);
        userAccountService.add(userAccountJohnSavings);

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setDateTime(OffsetDateTime.now());
        transaction.setOwner(user);
        transaction.setSender("NL ABNA 420 69");
        transaction.setReceiver("NL ABNA 69 70");
        transaction.setAmount(25.00);
        transaction.setDescription("Here's your money");
        transactionService.add(transaction);
    }
}
