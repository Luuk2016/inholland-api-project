package nl.inholland.inf2a.groep4.inhollandapiproject.service;

import nl.inholland.inf2a.groep4.inhollandapiproject.model.Account;
import nl.inholland.inf2a.groep4.inhollandapiproject.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    // Get all accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Add a new account
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }
}
