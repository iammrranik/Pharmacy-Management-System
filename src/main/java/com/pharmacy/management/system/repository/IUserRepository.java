package com.pharmacy.management.system.repository;

import com.pharmacy.management.system.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    int save(User user);
    Optional<User> findById(int id);
    Optional<User> findByPhone(String phone);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    List<User> findAllByPage(int pageNo, int pageSize);
    int count();
    int update(User user);
    int delete(int id);
    int deleteByPhone(String phone);
    int deleteByUsername(String username);
}
