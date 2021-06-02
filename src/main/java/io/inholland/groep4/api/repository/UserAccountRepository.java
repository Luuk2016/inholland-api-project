package io.inholland.groep4.api.repository;

import io.inholland.groep4.api.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByIBAN (String IBAN);

    boolean existsByIBAN(String IBAN);
}
