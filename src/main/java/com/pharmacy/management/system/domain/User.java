package com.pharmacy.management.system.domain;

import com.pharmacy.management.system.domain.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class User extends Person {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Role is required")
    private UserRole role;

    public User() {}

    public User(Integer id, String name, String email, String username,
                String password, String phone, UserRole role, LocalDateTime createdDate) {
        super(id, name, email, phone, createdDate);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + getId() + ", name=" + getName() + ", email=" + getEmail() +
                ", username=" + username + ", password=" + password + ", phone=" + getPhone() +
                ", role=" + role + ", createdDate=" + getCreatedDate() + '}';
    }
}
