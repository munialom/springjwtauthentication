package com.ctecx.springjwtauthentication.users;


import com.ctecx.springjwtauthentication.userroles.Role;
import com.ctecx.springjwtauthentication.userroles.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;




    public void createUser(UserEntity userEntity){
        encodePassword(userEntity);
        userRepository.save(userEntity);

    }

    public List<UserEntity> userList() {

        return (List<UserEntity>) userRepository.findAll();
    }

    public List<Role> roleList() {
        return (List<Role>) roleRepository.findAll();
    }

    private void encodePassword(UserEntity userEntity){

       String encodedPassword=passwordEncoder.encode(userEntity.getPassword());
      userEntity.setPassword(encodedPassword);
    }
}
