package com.emilpiekos.usersmanager.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @GetMapping("/login")
    public String login(@RequestParam(required = false, name = "logout") String logout,
                        @RequestParam(required = false, name = "error") String error,
                        @RequestParam(required = false, name = "registerSuccess") String success,
                        @RequestParam(required = false, name = "continue") String continu) {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/login?logout";
    }
}

