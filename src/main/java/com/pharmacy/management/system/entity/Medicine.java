package com.pharmacy.management.system.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Medicine {
    private int  id;
    private String name;
    private String category;
    private float price;
    private int availableQuantity;
    private String batchNo;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private LocalDateTime createdDateTime;

    public Medicine(int id, String name, String category, float price,
                    int availableQuantity, String batchNo,
                    LocalDate manufactureDate, LocalDate expiryDate, LocalDateTime createdDateTime) {
        this.setId(id);
        this.setName(name);
        this.setCategory(category);
        this.setPrice(price);
        this.setAvailableQuantity(availableQuantity);
        this.setBatchNo(batchNo);
        this.setManufactureDate(manufactureDate);
        this.setExpiryDate(expiryDate);
        this.setCreatedDateTime(createdDateTime);
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "medicine{" + "id=" + id + ", name=" + name + ", category=" + category
                + ", price=" + price + ", availableQuantity=" + availableQuantity
                + ", batchNo=" + batchNo + ", manufactureDate=" + manufactureDate
                + ", expiryDate=" + expiryDate + ", createdDateTime=" + createdDateTime + '}';
    }



}
