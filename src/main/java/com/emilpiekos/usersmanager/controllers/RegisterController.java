package com.emilpiekos.usersmanager.controllers;

import com.emilpiekos.usersmanager.user.UserDto;
import com.emilpiekos.usersmanager.user.UsersService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RegisterController {

    private final UsersService usersService;

    public RegisterController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/send-registration-form")
    public String register(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            try {
                usersService.save(userDto);
                return "redirect:/register-success";
            } catch (ConstraintViolationException e) {
                Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
                Set<String> collect = errors.stream()
                        .map(ConstraintViolation::getMessage).collect(Collectors.toSet());
                collect.forEach(error -> model.addAttribute("error", error));
                return "redirect:/error-page";
            }
        }
    }

    @GetMapping("/register-success")
    public String registerSuccess() {
        return "register-success";
    }
}
