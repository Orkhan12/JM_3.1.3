package org.iskandarov.SpringApp.controller;

import org.iskandarov.SpringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String userData(Model model, Principal principal) {
        model.addAttribute("userdata", this.userService.findByName(principal.getName()));
        return "user";
    }
}
