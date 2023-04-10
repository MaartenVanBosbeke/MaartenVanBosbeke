package com.shopr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopr.domains.User;
import com.shopr.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createAccount(User user){
        userRepository.createAccount(user);
    }

    public User findUserByNameAndPassword(User user){
        return userRepository.findUserByNameAndPassword(user);
    }

    public List<User> showListOfUsers() {
        return userRepository.showListOfUsers();
    }

    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    public void editUserInDb(User user) {
        userRepository.editUserInDb(user);
    }

    public void removeUserFromDb(long id) {
        userRepository.removeUserFromDb(id);
    }

    public List<User> searchUser(User userSearched) {
        return userRepository.searchUser(userSearched);
    }
}
