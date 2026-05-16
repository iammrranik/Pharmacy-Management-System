package com.pharmacy.management.system.domain;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class Supplier extends Person {

    @NotBlank(message = "Contact person name is required")
    private String contactPersonName;

    @NotBlank(message = "Address is required")
    private String address;

    public Supplier() {}

    public Supplier(Integer id, String name, String contactPersonName,
                    String email, String phone, String address, LocalDateTime createdDate) {
        super(id, name, email, phone, createdDate);
        this.setContactPersonName(contactPersonName);
        this.setAddress(address);
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + getId() + ", name=" + getName() +
                ", contactPersonName=" + contactPersonName + ", email=" + getEmail() +
                ", phone=" + getPhone() + ", address=" + address +
                ", createdDate=" + getCreatedDate() + '}';
    }
}
