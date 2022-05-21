package io.inholland.groep4.api.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserAccountTest {

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

