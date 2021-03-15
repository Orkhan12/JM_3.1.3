package org.iskandarov.SpringApp.rest;

import org.iskandarov.SpringApp.dto.exceptions.UserNotFoundByIdException;
import org.iskandarov.SpringApp.entities.User;

import org.iskandarov.SpringApp.service.RoleService;
import org.iskandarov.SpringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

import java.util.List;


@RestController
@RequestMapping("api/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) throws UserNotFoundByIdException {
        return this.userService.findById(id);
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        List<User> list = userService.findAll();
        return list;
    }

    @PostMapping("/add")
    public User newUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return this.userService.save(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return this.userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUsers(@PathVariable Long id) throws UserNotFoundByIdException{
        this.userService.deleteById(id);
    }

    @GetMapping("/user")
    public User userData(Principal principal) {
        return this.userService.findByName(principal.getName());
    }

}

