package com.emilpiekos.usersmanager.controllers;

import com.emilpiekos.usersmanager.user.User;
import com.emilpiekos.usersmanager.user.UsersService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String userPage(@RequestParam(required = false) String username, Model model) {
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUsername", currentUsername);
        model.addAttribute("isAdmin", isAdmin);
        return "user-page";
    }

    @GetMapping("/user-settings")
    public String profileSettings(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = usersService.findByUsername(username).orElse(null);
        model.addAttribute("currentUsername", username);
        model.addAttribute("user", user);
        return "user-settings";
    }

    @PostMapping("/save-changes")
    public String saveChanges(@ModelAttribute("user") User user) {
        String newPassword = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if (newPassword != null && newPassword.length() >= 8) {
            usersService.setPasswordWhereUsername(user.getUsername(), user.getPassword());
            return "redirect:/logout";
        }
        if (firstName != null && !firstName.isEmpty()) {
            usersService.setFirstNameWhereUsername(user.getUsername(), firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            usersService.setLastNameWhereUsername(user.getUsername(), lastName);
        }
        return "redirect:/";
    }

}