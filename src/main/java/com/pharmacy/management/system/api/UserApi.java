package com.pharmacy.management.system.api;

import com.pharmacy.management.system.domain.User;
import com.pharmacy.management.system.service.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody User user) {
        System.out.println("[UserApi] POST /api/users - saving user: " + user.getUsername());
        User saved = userService.saveUser(user);
        return ResponseEntity.status(201).body(Map.of(
            "message", "User created successfully",
            "data", saved
        ));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        System.out.println("[UserApi] GET /api/users - finding all");
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/page")
    public ResponseEntity<List<User>> findAllByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
        System.out.println("[UserApi] GET /api/users/page - page: " + pageNo + ", size: " + pageSize);
        return ResponseEntity.ok(userService.findAllUsersByPage(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        System.out.println("[UserApi] GET /api/users/" + id);
        var user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(404).body(Map.of("message", "User not found with id: " + id));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> findByPhone(@PathVariable String phone) {
        System.out.println("[UserApi] GET /api/users/phone/" + phone);
        var user = userService.findUserByPhone(phone);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(404).body(Map.of("message", "User not found with phone: " + phone));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        System.out.println("[UserApi] GET /api/users/username/" + username);
        var user = userService.findUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(404).body(Map.of("message", "User not found with username: " + username));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        System.out.println("[UserApi] GET /api/users/count");
        return ResponseEntity.ok(Map.of("count", userService.countUsers()));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody User user) {
        System.out.println("[UserApi] PUT /api/users - updating user id: " + user.getId());
        User updated = userService.updateUser(user);
        return ResponseEntity.ok(Map.of(
            "message", "User updated successfully",
            "data", updated
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        System.out.println("[UserApi] DELETE /api/users/" + id);
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    @DeleteMapping("/phone/{phone}")
    public ResponseEntity<Map<String, String>> deleteByPhone(@PathVariable String phone) {
        System.out.println("[UserApi] DELETE /api/users/phone/" + phone);
        userService.deleteUserByPhone(phone);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<Map<String, String>> deleteByUsername(@PathVariable String username) {
        System.out.println("[UserApi] DELETE /api/users/username/" + username);
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
