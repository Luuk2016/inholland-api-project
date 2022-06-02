package io.inholland.groep4.api.model;

import io.inholland.groep4.api.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserAccountTest {
    private UserAccount testUserAccount;

    @Autowired
    UserAccountService service = new UserAccountService();

    @BeforeEach
    void setup() {
        testUserAccount = new UserAccount();
        testUserAccount.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        testUserAccount.setAccountBalance(500.00);
        testUserAccount.setLowerLimit(100.00);
        testUserAccount.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
    }

    @Test
    public void createdUserAccountShouldNotBeNull() {
        assertNotNull(testUserAccount);
        System.out.println(testUserAccount);
    }

    @Test
    public void setIBANShouldSetThatIBAN() {
        String IBAN = service.getIBAN();
        testUserAccount.setIBAN(IBAN);
        assertEquals(IBAN, testUserAccount.getIBAN());
    }

    @Test
    public void setAccountBalanceShouldSetThatAmount() {
        Double balance = new Random().nextDouble();
        testUserAccount.setAccountBalance(balance);
        assertEquals(balance, testUserAccount.getAccountBalance());
    }

    @Test
    public void setLowerLimitShouldSetThatAmount() {
        Double limit = new Random().nextDouble();
        testUserAccount.setLowerLimit(limit);
        assertEquals(limit, testUserAccount.getLowerLimit());
    }

    @Test
    public void settingNegativeLowerLimitShouldThrowException() {
        Double limit = new Random().nextDouble();
        assertThrows(IllegalArgumentException.class, () -> testUserAccount.setLowerLimit(-limit));
    }

    @Test
    public void createUserAccountShouldNotBeNull() {
        UserAccount createdUserAccount = new UserAccount();
        createdUserAccount.setId(15L);
        createdUserAccount.setIBAN("NL01INHO0000000001");
        createdUserAccount.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        createdUserAccount.setAccountBalance(500.00);
        createdUserAccount.setLowerLimit(100.00);
        createdUserAccount.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        assertNotNull(createdUserAccount);
    }
}

