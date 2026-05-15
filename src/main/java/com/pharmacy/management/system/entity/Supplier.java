package com.pharmacy.management.system.entity;

import java.time.LocalDateTime;

public class Supplier {
    private int id;
    private String supplierName;
    private String contactPersonName;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createdDate;

    public Supplier(int id, String supplierName, String contactPersonName,
                    String email, String phone, String address, LocalDateTime createdDate) {
        this.setId(id);
        this.setSupplierName(supplierName);
        this.setContactPersonName(contactPersonName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setAddress(address);
        this.setCreatedDate(createdDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", supplierName=" + supplierName +
                ", contactPersonName=" + contactPersonName + ", email=" + email +
                ", phone=" + phone + ", address=" + address +
                ", createdDate=" + createdDate + '}';
    }
}
