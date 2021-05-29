package io.inholland.groep4.api.repository;

import io.inholland.groep4.api.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByOwner(String owner);
}
