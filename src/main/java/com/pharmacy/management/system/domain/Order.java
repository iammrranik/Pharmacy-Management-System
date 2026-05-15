package com.pharmacy.management.system.domain;

import com.pharmacy.management.system.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private String customerPhone;
    private LocalDateTime orderDateTime;
    private float totalAmount;
    private OrderStatus status;

    public Order(int id, String customerPhone, LocalDateTime orderDateTime,
                 float totalAmount, OrderStatus status) {
        this.setId(id);
        this.setCustomerPhone(customerPhone);
        this.setOrderDateTime(orderDateTime);
        this.setTotalAmount(totalAmount);
        this.setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customerPhone=" + customerPhone +
                ", orderDateTime=" + orderDateTime + ", totalAmount=" + totalAmount +
                ", status=" + status + '}';
    }
}
