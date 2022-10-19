package com.digitalways.chillandfish.repositories;

import com.digitalways.chillandfish.persistence.User;

import java.util.Optional;

/**
 * @author huba
 */
public interface CustomUserRepository {
    //custom method declarations

    Optional<User> findActiveUserByDisplayNameAndPassword(String pLoginName, String pPassword);

    Optional<User> findActiveUserByLoginname(String pLoginName);


}
