package com.emilpiekos.usersmanager.user;

import com.emilpiekos.usersmanager.role.Role;
import com.emilpiekos.usersmanager.role.UserRole;
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

    public void save(User user) {
        User userToRegister = new User();
        userToRegister.setUsername(user.getUsername());
        userToRegister.setPassword(
                passwordEncoder.encode(user.getPassword()
                ));
        userToRegister.setFirstName(user.getFirstName());
        userToRegister.setLastName(user.getLastName());
        userToRegister.setRoles(new HashSet<>(List.of(new UserRole(userToRegister, Role.ROLE_USER))));
        userToRegister.setEmail(user.getEmail());
        userToRegister.setPhoneNumber(user.getPhoneNumber());
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

    public void setPhoneNumberWhereUsername(String username, Long phoneNumber) {
        usersRepository.setPhoneNumberWhereUsername(username, phoneNumber);
    }
}