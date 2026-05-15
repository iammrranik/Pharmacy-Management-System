package com.pharmacy.management.system.service;

import com.pharmacy.management.system.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User saveUser(User user);
    Optional<User> findUserById(int id);
    Optional<User> findUserByPhone(String phone);
    Optional<User> findUserByUsername(String username);
    List<User> findAllUsers();
    List<User> findAllUsersByPage(int pageNo, int pageSize);
    long countUsers();
    User updateUser(User user);
    void deleteUser(int id);
    void deleteUserByPhone(String phone);
    void deleteUserByUsername(String username);
}
