package org.iskandarov.SpringApp.service;

import org.iskandarov.SpringApp.entities.User;

import java.util.List;

public interface UserService {
    User save(User u);

    User findByEmail(String email);

    User findByName(String name);

    User findById(Long id);

    User getOne(Integer integer);

    void deleteById(Long id);

    List<User> findAll();
}
