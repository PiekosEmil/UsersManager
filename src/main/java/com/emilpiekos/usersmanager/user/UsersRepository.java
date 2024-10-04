package com.emilpiekos.usersmanager.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    @Query("select u from User u")
    List<User> findAllUsers();

    @Transactional
    void deleteUserByUsername(String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.firstName = :firstName where u.username = :username")
    void setFirstNameWhereUsername(@Param("username") String username, @Param("firstName") String firstName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.lastName = :lastName where u.username = :username")
    void setLastNameWhereUsername(@Param("username") String username, @Param("lastName") String lastName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = :password where u.username = :username")
    void setPasswordWhereUsername(@Param("username") String username, @Param("password") String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.email = :email where u.username = :username")
    void setEmailWhereUsername(@Param("username") String username, @Param("email") String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.phoneNumber = :phoneNumber where u.username = :username")
    void setPhoneNumberWhereUsername(@Param("username") String username, @Param("phoneNumber") Long phoneNumber);
}
