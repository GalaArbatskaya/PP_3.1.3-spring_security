package com.galinaarbatskaya.PP_313.spring_security.controllers;

import com.galinaarbatskaya.PP_313.spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUserInfo(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(
                principal.getName()).get());
        return "myUser";
    }
}