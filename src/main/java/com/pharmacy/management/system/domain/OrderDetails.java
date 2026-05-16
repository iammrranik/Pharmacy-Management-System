package com.pharmacy.management.system.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class OrderDetails {
    private Integer id;

    @Min(value = 1, message = "Order ID is required")
    private Integer orderId;

    @Min(value = 1, message = "Medicine ID is required")
    private Integer medicineId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Positive(message = "Unit price must be positive")
    private Float unitPrice;

    public OrderDetails() {}

    public OrderDetails(Integer id, Integer orderId, Integer medicineId, Integer quantity, Float unitPrice) {
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "id=" + id + ", orderId=" + orderId +
                ", medicineId=" + medicineId + ", quantity=" + quantity +
                ", unitPrice=" + unitPrice + '}';
    }
}
