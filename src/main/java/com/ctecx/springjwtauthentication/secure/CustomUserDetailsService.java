package com.ctecx.springjwtauthentication.secure;

import com.ctecx.springjwtauthentication.userroles.Role;
import com.ctecx.springjwtauthentication.users.UserEntity;
import com.ctecx.springjwtauthentication.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("UserName Not Found"));

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), grantedAuthorityCollection(userEntity.getRoles()));
    }

    private Collection<GrantedAuthority> grantedAuthorityCollection(Set<Role> roleSet) {

        return roleSet.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
    }

}
