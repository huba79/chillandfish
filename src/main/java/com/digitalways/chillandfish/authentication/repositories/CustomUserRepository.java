package com.digitalways.chillandfish.authentication.repositories;

import com.digitalways.chillandfish.authentication.persistence.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author huba
 */
    public interface CustomUserRepository {
    //custom method declarations

    Optional<User> findActiveUserByDisplayNameAndPassword(String pLoginName, String pPassword);
    public Optional<User> findActiveUserByLoginname(String pLoginName);


}
