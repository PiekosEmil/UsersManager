package com.emilpiekos.usersmanager.role;

import com.emilpiekos.usersmanager.user.User;
import com.emilpiekos.usersmanager.user.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void addAdminRole(Long userId) {
        User user = usersRepository.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + userId + " does not exist"));

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(Role.ROLE_ADMIN);

        userRoleRepository.save(userRole);
    }

    public void deleteAdminRole(Long userId) {
        User user = usersRepository.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + userId + " does not exist"));
        Long roleId = 0L;
        if (isAdmin(user.getUsername())) {
            UserRole role = userRoleRepository.findByUserIdAndRole(user.getId(), Role.ROLE_ADMIN);
            roleId = role.getId();
            UserRole roleToRemove = userRoleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role not found"));
            user.getRoles().remove(roleToRemove);
            userRoleRepository.deleteById(roleId);
            usersRepository.save(user);
        } else {
            throw new IllegalArgumentException("Role not found");
        }
    }

    public boolean isAdmin(String username) {
        Optional<User> user = usersRepository.findUserByUsername(username);
        if (user.isPresent()) {
            for (UserRole role : user.get().getRoles()) {
                if (role.getRole().equals(Role.ROLE_ADMIN)) {
                    return true;
                }
            }
        }
        return false;
    }
}