package com.pharmacy.management.system.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Medicine {
    private Integer id;

    @NotBlank(message = "Medicine name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Price must be positive")
    private Float price;

    @Min(value = 0, message = "Available quantity must be zero or positive")
    private Integer availableQuantity;

    @NotBlank(message = "Batch number is required")
    private String batchNo;

    @NotNull(message = "Manufacture date is required")
    private LocalDate manufactureDate;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    private LocalDateTime createdDateTime;

    public Medicine() {}

    public Medicine(Integer id, String name, String category, Float price,
                    Integer availableQuantity, String batchNo,
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
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
