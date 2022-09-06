package com.digitalways.chillandfish.authentication.repositories;

import com.digitalways.chillandfish.authentication.persistence.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huba
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    
    Optional<User> findUserByLoginNameAndPassword(String loginName, String password /*, Boolean active */);

}
