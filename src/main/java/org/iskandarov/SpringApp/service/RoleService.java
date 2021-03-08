package org.iskandarov.SpringApp.service;

import org.iskandarov.SpringApp.entities.Role;
import org.springframework.data.domain.Example;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findByRole(String role);


    Role getOne(Integer integer);
}
