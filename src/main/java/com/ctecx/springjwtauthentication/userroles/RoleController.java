package com.ctecx.springjwtauthentication.userroles;


import com.ctecx.springjwtauthentication.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;
    @Autowired
    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/userRoles")
    public String registerUserRoles(Model model){

        model.addAttribute("roleUser", new Role());
        model.addAttribute("rolesData",roleService.roleList());

        return "settings/roles";
    }



    @PostMapping("/saveRole")
    public String saveUserRoles(Role role){

        roleService.createRole(role);
        return "redirect:/roles/userRoles";
    }

}
