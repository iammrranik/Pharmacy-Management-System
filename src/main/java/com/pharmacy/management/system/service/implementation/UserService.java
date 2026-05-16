package com.pharmacy.management.system.service.implementation;

import com.pharmacy.management.system.domain.User;
import com.pharmacy.management.system.repository.implementation.UserRepository;
import com.pharmacy.management.system.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        if (user.getCreatedDate() == null) {
            user.setCreatedDate(LocalDateTime.now());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("[UserService] saveUser called for username: " + user.getUsername());
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findUserById(int id) {
        System.out.println("[UserService] findUserById called for id: " + id);
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByPhone(String phone) {
        System.out.println("[UserService] findUserByPhone called for phone: " + phone);
        return userRepository.findByPhone(phone);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        System.out.println("[UserService] findUserByUsername called for username: " + username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        System.out.println("[UserService] findAllUsers called");
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllUsersByPage(int pageNo, int pageSize) {
        System.out.println("[UserService] findAllUsersByPage called - page: " + pageNo + ", size: " + pageSize);
        return userRepository.findAllByPage(pageNo, pageSize);
    }

    @Override
    public int countUsers() {
        System.out.println("[UserService] countUsers called");
        return userRepository.count();
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        System.out.println("[UserService] updateUser called for id: " + user.getId());
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$") && !user.getPassword().startsWith("$2b$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.update(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        System.out.println("[UserService] deleteUser called for id: " + id);
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteUserByPhone(String phone) {
        System.out.println("[UserService] deleteUserByPhone called for phone: " + phone);
        userRepository.deleteByPhone(phone);
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        System.out.println("[UserService] deleteUserByUsername called for username: " + username);
        userRepository.deleteByUsername(username);
    }
}
