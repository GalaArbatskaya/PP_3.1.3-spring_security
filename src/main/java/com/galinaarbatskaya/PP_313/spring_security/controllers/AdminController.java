package com.galinaarbatskaya.PP_313.spring_security.controllers;

import com.galinaarbatskaya.PP_313.spring_security.models.User;
import com.galinaarbatskaya.PP_313.spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public String users(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsers";
    }


    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.save(user);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @PathVariable("id") long id) {
        if (bindingResult.hasErrors()){
            return "edit";
        }
        userService.update(user);
        return "redirect:/admin/allUsers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin/allUsers";
    }

}

