package com.emilpiekos.usersmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ErrorController {

    @GetMapping("/error-page")
    public String errorPage(@ModelAttribute("error") String error, Model model) {
        model.addAttribute("error", error);
        return "error-page";
    }
}
