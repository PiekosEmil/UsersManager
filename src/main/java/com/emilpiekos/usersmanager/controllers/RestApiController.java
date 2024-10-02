package com.emilpiekos.usersmanager.controllers;

import com.emilpiekos.usersmanager.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestApiController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/check-username")
    public Map<String, String> checkUsername(@RequestParam String username) {
        Map<String, String> response = new HashMap<>();
        if (usersService.usernameExists(username)) {
            response.put("message", "Username already taken.");
        } else {
            response.put("message", "Username available.");
        }
        return response;
    }
}
