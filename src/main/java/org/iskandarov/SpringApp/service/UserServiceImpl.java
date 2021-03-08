package org.iskandarov.SpringApp.service;

import org.iskandarov.SpringApp.entities.User;
import org.iskandarov.SpringApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

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
    public User findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User getOne(Integer integer) {
        return this.userRepository.getOne(integer);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
