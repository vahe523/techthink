package com.example.techthink.service.impl;

import com.example.techthink.persistence.model.Role;
import com.example.techthink.persistence.repository.RoleRepository;
import com.example.techthink.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String name){
        return roleRepository.findByName(name);
    }

    @Override
    public Role getRoleById(Long id){
        return roleRepository.getById(id);
    }
}
