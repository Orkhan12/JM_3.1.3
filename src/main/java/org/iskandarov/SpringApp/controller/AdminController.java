package org.iskandarov.SpringApp.controller;

import org.iskandarov.SpringApp.entities.Role;
import org.iskandarov.SpringApp.entities.User;
import org.iskandarov.SpringApp.repositories.RoleRepository;
import org.iskandarov.SpringApp.repositories.UserRepository;
import org.iskandarov.SpringApp.service.RoleService;
import org.iskandarov.SpringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@Transactional
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String listUsers(Model model, Principal principal) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.findAll());
        model.addAttribute("listRole", this.roleService.findAll());

        return "admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUsers(@ModelAttribute("admin") User user, @RequestParam(value = "select_role", required = false) String[] role) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> set = new HashSet<>();
        for (String r : role){
            if (role.length == 0) {
                set.add(this.roleService.findByRole("ROLE_USER"));
            } else {
                set.add(this.roleService.findByRole(r));
            }
        }
        user.setRoles(set);
        this.userService.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("remove/{id}")
    public String removeUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        this.userService.deleteById(id);
        return "redirect:/admin";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", this.userService.findById(id));

        return "admin";

    }


    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateUser(User user, @RequestParam(value = "select_roles", required = false) String[] role) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> setRole = new HashSet<>();
        for (String r : role){
            if (role.length == 0) {
                setRole.add(this.roleService.findByRole("ROLE_USER"));
            } else {
                setRole.add(this.roleService.findByRole(r));
            }
        }

        user.setRoles(setRole);
        this.userService.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", this.userService.findById(id));

        return "userdata";

    }

}

