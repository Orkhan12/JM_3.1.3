package org.iskandarov.SpringApp.service;

import org.iskandarov.SpringApp.entities.Role;
import org.iskandarov.SpringApp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceIml implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role findByRole(String role) {
        return this.roleRepository.findByRole(role);
    }

    @Override
    public Role getOne(Integer integer) {
        return this.roleRepository.getOne(integer);
    }
}
