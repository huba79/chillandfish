package com.digitalways.chillandfish.repositories;

import com.digitalways.chillandfish.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author huba
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

    Optional<User> findUserByLoginNameAndPassword(String loginName, String password /*, Boolean active */);

    Optional<User> findById(Long userId);

}
