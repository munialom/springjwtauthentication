package com.ctecx.springjwtauthentication.users;


import com.ctecx.springjwtauthentication.userroles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="User")
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128, nullable = false , unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    public String phone;
    private boolean enabled;

    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="role_id"))
    private Set<Role> roles= new HashSet<>();

    public boolean hasRole(String roleName){

        Iterator<Role> roleIterator=roles.iterator();
        while(roleIterator.hasNext()){
            Role role=roleIterator.next();
            if(role.getRoleName().equals(roleName)){
                return true;
            }

        }
       return false;
    }
}
