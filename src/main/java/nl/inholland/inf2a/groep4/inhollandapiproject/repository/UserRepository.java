package nl.inholland.inf2a.groep4.inhollandapiproject.repository;

import nl.inholland.inf2a.groep4.inhollandapiproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
