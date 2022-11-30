package com.ctecx.springjwtauthentication.users;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/users")
public class UserController {
    private  final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;

    }


    @GetMapping("/newUser")
    public String registerUser(Model model){
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(true);
        model.addAttribute("userd", userEntity);
        model.addAttribute("rolesData",userService.roleList());
        model.addAttribute("usersList",userService.userList());


        return "settings/all_users";
    }


    @PostMapping("/save")
    public String saveUser(UserEntity userEntity, HttpServletRequest request) {
        userService.createUser(userEntity);

        return "redirect:/users/newUser";
    }


}
