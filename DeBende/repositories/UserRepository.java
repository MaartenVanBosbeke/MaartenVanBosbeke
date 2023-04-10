package com.shopr.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shopr.domains.User;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> showListOfUsers() {
        try {
            return entityManager.createNamedQuery("findAllUsers", User.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public User findUserById(long id) {
        try {
            return entityManager.createNamedQuery("findUserById", User.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User findUserByNameAndPassword(User user){
        try {
            User userFound = entityManager.createNamedQuery("checkIfLoginMatches", User.class)
                    .setParameter("name", user.getUserName())
                    .setParameter("password", user.getUserPassword())
                    .getSingleResult();
            return userFound;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void createAccount(User user){
        entityManager.persist(user);
    }

    @Transactional
    public void editUserInDb(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void removeUserFromDb(long id) {
        User userFound = findUserById(id);
        entityManager.remove(userFound);
    }

    public List<User> searchUser(User userSearched) {
        try {
            List<User> usersFound = entityManager.createNamedQuery("findUserBySearch", User.class)
                    .setParameter("id", userSearched.getId())
                    .setParameter("userName", userSearched.getUserName())
                    .setParameter("userPassword", userSearched.getUserPassword())
                    .setParameter("userSecLvl", userSearched.getSecurityLevel())
                    .getResultList();
            return usersFound;
        } catch (Exception e) {
            return null;
        }
    }
}
