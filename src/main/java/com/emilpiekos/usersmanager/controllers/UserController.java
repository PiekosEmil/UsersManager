package com.emilpiekos.usersmanager.controllers;

import com.emilpiekos.usersmanager.location.GeolocationService;
import com.emilpiekos.usersmanager.location.Location;
import com.emilpiekos.usersmanager.user.User;
import com.emilpiekos.usersmanager.user.UserDto;
import com.emilpiekos.usersmanager.user.UsersService;
import com.emilpiekos.usersmanager.weather.WeatherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    private final UsersService usersService;
    private final GeolocationService geolocationService;
    private final WeatherService weatherService;

    public UserController(UsersService usersService, GeolocationService geolocationService, WeatherService weatherService1) {
        this.usersService = usersService;
        this.geolocationService = geolocationService;
        this.weatherService = weatherService1;
    }

    @GetMapping("/")
    public String userPage(@RequestParam(required = false) String username, Model model) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = usersService.findByUsername(currentUsername);
        if (user.isPresent()) {
            String address = user.get().getAddress();
            model.addAttribute("address", address);
            try {
                Location locationCoordinates = geolocationService.getLocationCoordinates(address);
                Set<String> weatherParameters = weatherService.getWeatherParameters(locationCoordinates);
                model.addAttribute("weatherParameters", weatherParameters);
            } catch (NullPointerException e) {
                model.addAttribute("weatherParameters", "Couldn't get location coordinates");
            }

        }
        model.addAttribute("currentUsername", currentUsername);
        return "user-page";
    }

    @GetMapping("/user-settings")
    public String profileSettings(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = usersService.findByUsername(username);
        if (user.isPresent()) {
            UserDto userDto = usersService.mapUserToUserDto(user.get());
            model.addAttribute("currentUsername", username);
            model.addAttribute("userDto", userDto);
            return "user-settings";
        } else {
            return "redirect:/error-page";
        }
    }

    @PostMapping("/save-changes")
    public String saveChanges(@ModelAttribute("userDto") UserDto userDto) {
        User user = usersService.mapUserDtoToUser(userDto);
        String newPassword = userDto.getPassword();
        String newFirstName = userDto.getFirstName();
        String newLastName = userDto.getLastName();
        String newEmail = userDto.getEmail();
        String newPhoneNumber = userDto.getPhoneNumber();
        if (newFirstName != null && !newFirstName.isEmpty()) {
            usersService.setFirstNameWhereUsername(user.getUsername(), newFirstName);
        }
        if (newLastName != null && !newLastName.isEmpty()) {
            usersService.setLastNameWhereUsername(user.getUsername(), newLastName);
        }
        if (newEmail != null && !newEmail.isEmpty()) {
            usersService.setEmailWhereUsername(user.getUsername(), newEmail);
        }
        if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
            usersService.setPhoneNumberWhereUsername(user.getUsername(), newPhoneNumber);
        }
        if (newPassword != null && newPassword.length() >= 8) {
            usersService.setPasswordWhereUsername(user.getUsername(), user.getPassword());
            return "redirect:/logout";
        }
        return "redirect:/";
    }
}
