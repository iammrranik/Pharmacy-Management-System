package com.pharmacy.management.system.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class OrderDetails {
    private Integer id;

    @Min(value = 1, message = "Order ID is required")
    private int orderId;

    @Min(value = 1, message = "Medicine ID is required")
    private int medicineId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Positive(message = "Unit price must be positive")
    private float unitPrice;

    public OrderDetails() {}

    public OrderDetails(Integer id, int orderId, int medicineId, int quantity, float unitPrice) {
        this.setId(id);
        this.setOrderId(orderId);
        this.setMedicineId(medicineId);
        this.setQuantity(quantity);
        this.setUnitPrice(unitPrice);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "id=" + id + ", orderId=" + orderId +
                ", medicineId=" + medicineId + ", quantity=" + quantity +
                ", unitPrice=" + unitPrice + '}';
    }
}
