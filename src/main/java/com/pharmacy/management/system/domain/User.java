package com.pharmacy.management.system.domain;

import com.pharmacy.management.system.domain.enums.UserRole;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String phone;
    private UserRole role;
    private LocalDateTime createdDate;

    public User(int id, String name, String email, String username,
                String password, String phone, UserRole role, LocalDateTime createdDate) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setUsername(username);
        this.setPassword(password);
        this.setPhone(phone);
        this.setRole(role);
        this.setCreatedDate(createdDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email +
                ", username=" + username + ", password=" + password + ", phone=" + phone +
                ", role=" + role + ", createdDate=" + createdDate + '}';
    }
}
