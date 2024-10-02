package com.emilpiekos.usersmanager.role;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    @Transactional
    void deleteById(Long id);

    UserRole findByUserIdAndRole(Long userId, Role role);
}

