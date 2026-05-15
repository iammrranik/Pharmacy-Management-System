package com.pharmacy.management.system.domain;

import com.pharmacy.management.system.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int customerId;
    private String customerPhone;
    private LocalDateTime orderDateTime;
    private LocalDateTime returnDateTime;
    private float totalAmount;
    private float refundAmount = 0;
    private OrderStatus status;
    private int sellerId;

    public Order(int id, int customerId, String customerPhone, LocalDateTime orderDateTime,
                 LocalDateTime returnDateTime, float totalAmount, float refundAmount,
                 OrderStatus status, int sellerId) {
        this.setId(id);
        this.setCustomerId(customerId);
        this.setCustomerPhone(customerPhone);
        this.setOrderDateTime(orderDateTime);
        this.setReturnDateTime(returnDateTime);
        this.setTotalAmount(totalAmount);
        this.setRefundAmount(refundAmount);
        this.setStatus(status);
        this.setSellerId(sellerId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(float refundAmount) {
        this.refundAmount = refundAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customerId=" + customerId +
                ", customerPhone=" + customerPhone + ", orderDateTime=" + orderDateTime +
                ", returnDateTime=" + returnDateTime + ", totalAmount=" + totalAmount +
                ", refundAmount=" + refundAmount + ", status=" + status +
                ", sellerId=" + sellerId + '}';
    }
}
