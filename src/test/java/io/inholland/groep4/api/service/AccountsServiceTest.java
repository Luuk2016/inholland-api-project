package io.inholland.groep4.api.service;

import io.inholland.groep4.api.model.Role;
import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.security.JwtTokenProvider;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BOOLEAN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountsServiceTest {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;

    @Autowired
    public JwtTokenProvider jwtTokenProvider;

    @Test
    public void gettingAllAccountsShouldGiveListOfUsers() {
        List<UserAccount> accounts = userAccountService.getAllAccounts();

        assertThat(accounts).isNotEmpty();
        assertThat(accounts.get(0).getAccountBalance()).isEqualTo(500.0);
    }
    @Test
    public void gettingAccountsByIdShouldGiveAccount() {
        Long idToFind = 1l;
        UserAccount account = userAccountService.getAccountById(idToFind);

        assertThat(account).isNotNull();
        assertThat(account.getAccountBalance()).isEqualTo(500.0);
    }

    @Test
    public void GettingAccountWithWrongIDShouldThrowException(){
        Long idToFind = 100l;

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userAccountService.getAccountById(idToFind);
        });

        assertTrue(exception.getMessage().contains("Id not found"));
    }

    @Test
    public void gettingAccountByUserShouldGiveUserAccount(){
        String usernameToFind = "test-user1";
        User username = userService.findByUsername(usernameToFind);
        List<UserAccount> userAccount = userAccountService.getAccountsByUser(username);

        assertThat(userAccount).isNotEmpty();
        assertThat(username.getUsername()).isEqualTo("test-user1");
    }
    @Test
    public void AddIncorrectNewIBAN(){
        User user = new User();
        user.setUsername("peter");
        user.setPassword("test");
        user.setFirstName("Peter");
        user.setLastName("Griffin");
        user.setEmail("peter@example.com");
        user.setBirthdate("01/01/1970");

        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount.setOwner(user);
        userAccount.setAccountBalance(0.00);
        userAccount.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount.setLowerLimit(100.00);
        userAccount.setIBAN("NL01INHO0000000001");

        String IBAN = userAccountService.getIBAN();
        boolean ExistByIBAN = userAccountService.existByIBAN(IBAN);
        UserAccount newUser = userAccountService.add(userAccount, ExistByIBAN);

        assertThat(newUser).isNotNull();
    }

    @Test
    public void CheckIfAccountBelongToUser(){
        String usernameToFind = "test-user1";
        User username = userService.findByUsername(usernameToFind);
        Long idToFind = 8l;

        boolean account = userAccountService.checkIfAccountBelongsToOwner(username, idToFind);
        assertThat(account).isEqualTo(true);
    }

    @Test
    public void IfAccountDoesNotBelongToUserShouldThrowException(){
        String usernameToFind = "test-user1";
        User username = userService.findByUsername(usernameToFind);
        Long idToFind = 100l;

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userAccountService.checkIfAccountBelongsToOwner(username, idToFind);
        });

        assertFalse(exception.getMessage().contains("Account not found"));
    }

    @Test
    public void savingUserAccount()
    {
        Long idToFind = 1l;
        UserAccount account = userAccountService.getAccountById(idToFind);
        UserAccount userAccount = userAccountService.save(account);

        assertThat(userAccount.getAccountBalance()).isEqualTo(500.0);
    }

    @Test
    public void gettingIBAN(){
        String IBAN = userAccountService.getIBAN();

        assertThat(IBAN).isNotNull();
    }

    @Test
    public void generatingAlreadyExistedIBAN(){
        String IBAN = userAccountService.getIBAN();

        Long idToFind = 1l;
        UserAccount account = userAccountService.getAccountById(idToFind);
        UserAccount userAccount = userAccountService.save(account);

        if(IBAN.equals(userAccount.getIBAN()))
        {
            IBAN = new Iban.Builder().countryCode(CountryCode.NL).bankCode("INHO").buildRandom().toString();
        }
        assertThat(userAccount.getIBAN()).isNotEqualTo(IBAN);
    }

    @Test
    public void TryToSaveNotExistingAccountShouldThrowException(){
        UserAccount user = new UserAccount();

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userAccountService.save(user);
        });

        assertTrue(exception.getMessage().contains("No accounts found"));
    }
}
