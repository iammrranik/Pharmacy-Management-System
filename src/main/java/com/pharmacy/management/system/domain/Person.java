package com.pharmacy.management.system.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public abstract class Person {
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    private LocalDateTime createdDate;

    public Person() {}

    public Person(Integer id, String name, String email, String phone, LocalDateTime createdDate) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.setCreatedDate(createdDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", email=" + email +
                ", phone=" + phone + ", createdDate=" + createdDate + '}';
    }
}
