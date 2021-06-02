package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount add(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
        return userAccount;
    }

    public List<UserAccount> getAllAccounts() { return userAccountRepository.findAll(); }

    public boolean existByIBAN(String iban) { return userAccountRepository.existsByIBAN(iban); }
  
    public UserAccount getSpecificAccount(Long id) { return userAccountRepository.getUserAccountById(id); }
}
