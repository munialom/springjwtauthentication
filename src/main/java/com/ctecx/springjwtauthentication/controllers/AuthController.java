package com.ctecx.springjwtauthentication.controllers;

import com.ctecx.springjwtauthentication.dtos.AuthResposeDto;
import com.ctecx.springjwtauthentication.dtos.LoginDto;
import com.ctecx.springjwtauthentication.dtos.RegisterDto;
import com.ctecx.springjwtauthentication.secure.JWTGenerator;
import com.ctecx.springjwtauthentication.userroles.Role;
import com.ctecx.springjwtauthentication.userroles.RoleRepository;
import com.ctecx.springjwtauthentication.users.UserEntity;
import com.ctecx.springjwtauthentication.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is Taken", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<String> strRoles = registerDto.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {

            Role userRole = roleRepository.findByRoleName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

        });

        userEntity.setRoles(roles);
        userRepository.save(userEntity);

        return new ResponseEntity<>("User Registered success !", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResposeDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResposeDto(token), HttpStatus.OK);

    }




}
