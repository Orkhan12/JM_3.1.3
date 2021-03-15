package org.iskandarov.SpringApp.service;

import org.iskandarov.SpringApp.dto.exceptions.UserNotFoundByIdException;
import org.iskandarov.SpringApp.entities.User;
import org.iskandarov.SpringApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public User save(User u) {

        return this.userRepository.save(u);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public User findById(Long id) throws UserNotFoundByIdException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundByIdException(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws UserNotFoundByIdException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundByIdException(id);
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
