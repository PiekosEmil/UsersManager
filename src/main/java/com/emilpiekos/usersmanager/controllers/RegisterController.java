package com.emilpiekos.usersmanager.controllers;

import com.emilpiekos.usersmanager.user.User;
import com.emilpiekos.usersmanager.user.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UsersService usersService;

    public RegisterController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/send-registration-form")
    public String register(@ModelAttribute("user") User user) {
        usersService.save(user);
        return "redirect:/register-success";
    }

    @GetMapping("/register-success")
    public String registerSuccess() {
        return "register-success";
    }
}
