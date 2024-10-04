package com.emilpiekos.usersmanager.user;

import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersService usersService;

    public CustomUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = usersService.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Collection<SimpleGrantedAuthority> role =
                    user.getRoles().stream()
                            .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().name()))
                            .collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), role);
        }
        throw new UsernameNotFoundException("User not found");
    }
}
