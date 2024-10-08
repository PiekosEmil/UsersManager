package com.emilpiekos.usersmanager.controllers;

import com.emilpiekos.usersmanager.role.UserRoleService;
import com.emilpiekos.usersmanager.user.User;
import com.emilpiekos.usersmanager.user.UsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UsersService usersService;
    private final UserRoleService userRoleService;

    public AdminController(UsersService usersService, UserRoleService userRoleService) {
        this.usersService = usersService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/admin-page")
    public String adminPanel(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("users", usersService.findAll());
        model.addAttribute("currentUser", username);
        return "admin-page";
    }

    @GetMapping("/manage-user")
    public String manageUser(@RequestParam String username, Model model) {
        boolean isAdmin = userRoleService.isAdmin(username);
        if (isAdmin) {
            model.addAttribute("isAdmin", "Tak");
        } else {
            model.addAttribute("isAdmin", "Nie");
        }
        model.addAttribute("username", username);
        return "manage-user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String username) {
        usersService.deleteUserByUsername(username);
        return "redirect:/admin-page";
    }

    @PostMapping("/update-role")
    public String updateRole(@RequestParam String username) {
        Optional<User> user = usersService.findByUsername(username);
        boolean isAdmin = userRoleService.isAdmin(username);
        if (user.isPresent() && !isAdmin) {
            userRoleService.addAdminRole(user.get().getId());
        }
        return "redirect:/manage-user?username=" + username;
    }

    @PostMapping("/delete-role")
    public String deleteRole(@RequestParam String username) {
        Optional<User> user = usersService.findByUsername(username);
        boolean isAdmin = userRoleService.isAdmin(username);
        if (user.isPresent() && isAdmin) {
            userRoleService.deleteAdminRole(user.get().getId());
        }
        return "redirect:/manage-user?username=" + username;
    }
}