package com.ctecx.springjwtauthentication.userroles;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private  final  RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public void createRole(Role role){

        roleRepository.save(role);
    }

    public List<Role> roleList(){

       return (List<Role>) roleRepository.findAll();
    }
}
