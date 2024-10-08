package com.emilpiekos.usersmanager.user;

import com.emilpiekos.usersmanager.role.Role;
import com.emilpiekos.usersmanager.role.UserRole;
import jakarta.validation.ConstraintViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return usersRepository.findUserByUsername(username);
    }

    public boolean usernameExists(String username) {
        return usersRepository.findUserByUsername(username).isPresent();
    }

    public List<User> findAll() {
        return usersRepository.findAllUsers();
    }

    public void deleteUserByUsername(String username) {
        usersRepository.deleteUserByUsername(username);
    }

    public void save(UserDto userDto) throws ConstraintViolationException {
        User userToRegister = mapUserDtoToUser(userDto);
        usersRepository.save(userToRegister);
    }

    public void setFirstNameWhereUsername(String username, String firstName) {
        usersRepository.setFirstNameWhereUsername(username, firstName);
    }

    public void setLastNameWhereUsername(String username, String lastName) {
        usersRepository.setLastNameWhereUsername(username, lastName);
    }

    public void setPasswordWhereUsername(String username, String password) {
        String encoded = passwordEncoder.encode(password);
        usersRepository.setPasswordWhereUsername(username, encoded);
    }

    public void setEmailWhereUsername(String username, String email) {
        usersRepository.setEmailWhereUsername(username, email);
    }

    public void setPhoneNumberWhereUsername(String username, String phoneNumber) {
        usersRepository.setPhoneNumberWhereUsername(username, phoneNumber);
    }

    public User mapUserDtoToUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encodedPassword);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRoles(new HashSet<>(List.of(new UserRole(user, Role.ROLE_USER))));
        return user;
    }

    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
}