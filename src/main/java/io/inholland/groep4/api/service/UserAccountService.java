package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.repository.UserAccountRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount add(UserAccount userAccount, boolean randomIBAN) {
        // Check if a random iban should me generated
        if (randomIBAN) {
            userAccount.setIBAN(getIBAN());
        }
        userAccountRepository.save(userAccount);
        return userAccount;
    }

    public String getIBAN() {
        // Generate a new IBAN
        Iban iban = new Iban.Builder().countryCode(CountryCode.NL).bankCode("INHO").buildRandom();

        // Check if not already in use
        // If in use, get a new one until it's unique
        while (existByIBAN(iban.toString())) {
            iban = new Iban.Builder().countryCode(CountryCode.NL).bankCode("INHO").buildRandom();
        }

        return iban.toString();
    }

    public List<UserAccount> getAllAccounts() { return userAccountRepository.findAll(); }

    public boolean existByIBAN(String iban) { return userAccountRepository.existsByIBAN(iban); }
  
    public UserAccount getAccountById(Long id) { return userAccountRepository.getUserAccountById(id); }

    public List<UserAccount> getAccountsByUser(User user) { return userAccountRepository.getUserAccountByUser(user); }

    public boolean checkIfAccountBelongsToOwner(User user, Long id) { return userAccountRepository.existsByOwnerAndId(user, id); }
}
