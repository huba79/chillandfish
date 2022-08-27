package com.digitalways.chillandfish.authentication.repositories;

import com.digitalways.chillandfish.authentication.persistence.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huba
 */
@Repository
public interface UserRepository extends CustomUserRepository<User,Long>, JpaRepository<User, Long> {
    
    Optional<User> findUserByLoginNameAndPassword(String loginName, String password /*, Boolean active */);
    Optional<User> findUserByLoginNameAndPasswordAndActive(String loginName, String password , Boolean active );
}
