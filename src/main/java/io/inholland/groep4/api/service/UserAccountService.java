package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.repository.UserAccountRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount add(UserAccount userAccount, boolean randomIBAN) {
        try{
            // Check if a random iban should me generated
            if (randomIBAN) {
                userAccount.setIBAN(getIBAN());
            }

            userAccountRepository.save(userAccount);
            return userAccount;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect iban given");
        }
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

    public List<UserAccount> getAllAccounts() {
        if (userAccountRepository.findAll().size() == 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No accounts found");
        }
        return userAccountRepository.findAll();
    }
  
    public UserAccount getAccountById(Long id) {
        if (userAccountRepository.getUserAccountById(id) == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Id not found");
        }
        return userAccountRepository.getUserAccountById(id);
    }

    public boolean checkIfAccountBelongsToOwner(User user, Long id) {
        if(!userAccountRepository.existsByOwnerAndId(user, id))
        {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Account does not belong to owner");
        }
        return userAccountRepository.existsByOwnerAndId(user, id);
    }

    public UserAccount save(UserAccount user) {
        if (userAccountRepository.getUserAccountById(user.getId()) == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No accounts found");
        }
        return userAccountRepository.save(user);
    }

    public List<UserAccount> getAccountsByUser(User user) {
        return userAccountRepository.getUserAccountsByOwner(user);
    }
    public boolean existByIBAN(String iban) {
        return userAccountRepository.existsByIBAN(iban);
    }
}
