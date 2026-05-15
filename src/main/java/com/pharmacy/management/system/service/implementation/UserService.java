package com.pharmacy.management.system.service.implementation;

import com.pharmacy.management.system.domain.User;
import com.pharmacy.management.system.repository.implementation.UserRepository;
import com.pharmacy.management.system.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        System.out.println("[UserService] saveUser called for username: " + user.getUsername());
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(int id) {
        System.out.println("[UserService] findUserById called for id: " + id);
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByPhone(String phone) {
        System.out.println("[UserService] findUserByPhone called for phone: " + phone);
        return userRepository.findByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        System.out.println("[UserService] findUserByUsername called for username: " + username);
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        System.out.println("[UserService] findAllUsers called");
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsersByPage(int pageNo, int pageSize) {
        System.out.println("[UserService] findAllUsersByPage called - page: " + pageNo + ", size: " + pageSize);
        return userRepository.findAllByPage(pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public int countUsers() {
        System.out.println("[UserService] countUsers called");
        return userRepository.count();
    }

    @Override
    public User updateUser(User user) {
        System.out.println("[UserService] updateUser called for id: " + user.getId());
        userRepository.update(user);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        System.out.println("[UserService] deleteUser called for id: " + id);
        userRepository.delete(id);
    }

    @Override
    public void deleteUserByPhone(String phone) {
        System.out.println("[UserService] deleteUserByPhone called for phone: " + phone);
        userRepository.deleteByPhone(phone);
    }

    @Override
    public void deleteUserByUsername(String username) {
        System.out.println("[UserService] deleteUserByUsername called for username: " + username);
        userRepository.deleteByUsername(username);
    }
}
