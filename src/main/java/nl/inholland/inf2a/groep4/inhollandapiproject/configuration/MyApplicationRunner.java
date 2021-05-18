package nl.inholland.inf2a.groep4.inhollandapiproject.configuration;

import nl.inholland.inf2a.groep4.inhollandapiproject.enums.AccountType;
import nl.inholland.inf2a.groep4.inhollandapiproject.enums.UserType;
import nl.inholland.inf2a.groep4.inhollandapiproject.model.Account;
import nl.inholland.inf2a.groep4.inhollandapiproject.model.Transaction;
import nl.inholland.inf2a.groep4.inhollandapiproject.model.User;
import nl.inholland.inf2a.groep4.inhollandapiproject.service.AccountService;
import nl.inholland.inf2a.groep4.inhollandapiproject.service.TransactionService;
import nl.inholland.inf2a.groep4.inhollandapiproject.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public MyApplicationRunner(UserService userService, AccountService accountService, TransactionService transactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create two new users
        User user1 = new User(1,"John", "Doe", UserType.CUSTOMER, 100.00, 1500.00, 500.00);
        User user2 = new User(2,"Luuk", "Kenselaar", UserType.EMPLOYEE, 100.00, 1500.00, 500.00);

        // Add the users
        userService.addUser(user1);
        userService.addUser(user2);

        // Create a new account
        Account account = new Account(1, 1, "KAAS", AccountType.CURRENT, 100.00, 50.00);

        // Add the account
        accountService.addAccount(account);

        // Create a new transaction
        Transaction transaction = new Transaction(1, LocalDate.now(), 1, "KAAS", "PIZZA", 100.00);

        // Add the new transaction
        transactionService.addTransaction(transaction);
    }
}
