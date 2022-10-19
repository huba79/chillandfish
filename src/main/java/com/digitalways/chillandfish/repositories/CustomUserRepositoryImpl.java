/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.repositories;

import com.digitalways.chillandfish.persistence.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

/**
 * @author huba
 */
@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<User> findActiveUserByDisplayNameAndPassword(String pLoginName, String pPassword) {

        String queryString = "SELECT * FROM USERS WHERE ACTIVE = TRUE AND LOGIN_NAME = ?1 AND PASSWORD = ?2";
        Query query = entityManager.createNativeQuery(queryString, User.class);
        query.setParameter(1, pLoginName);
        query.setParameter(2, pPassword);
        Optional<User> empty = Optional.empty();
        return (Optional<User>) query.getResultList().stream().findFirst();
    }

    public Optional<User> findActiveUserByLoginname(String pLoginName) {

        String queryString = "SELECT * FROM USERS WHERE ACTIVE = TRUE AND LOGIN_NAME = ?1";
        Query query = entityManager.createNativeQuery(queryString, User.class);
        query.setParameter(1, pLoginName);
        Optional<User> empty = Optional.empty();
        //TODO: handle
        return (Optional<User>) query.getResultList().stream().findFirst();
    }


    //custom method implementations

}
