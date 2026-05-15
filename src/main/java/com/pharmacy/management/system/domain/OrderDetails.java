package com.pharmacy.management.system.domain;

public class OrderDetails {
    private int id;
    private int orderId;
    private int medicineId;
    private int quantity;
    private float unitPrice;

    public OrderDetails(int id, int orderId, int medicineId, int quantity, float unitPrice) {
        this.setId(id);
        this.setOrderId(orderId);
        this.setMedicineId(medicineId);
        this.setQuantity(quantity);
        this.setUnitPrice(unitPrice);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
