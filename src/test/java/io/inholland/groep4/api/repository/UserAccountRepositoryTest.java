package io.inholland.groep4.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository userAccountRepository;

}
