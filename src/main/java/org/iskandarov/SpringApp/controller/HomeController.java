package org.iskandarov.SpringApp.controller;

import org.iskandarov.SpringApp.entities.User;
import org.iskandarov.SpringApp.repositories.UserRepository;
import org.iskandarov.SpringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class HomeController {

    @Autowired
    private UserService userService;


    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
