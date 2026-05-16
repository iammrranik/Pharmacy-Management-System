package com.pharmacy.management.system.domain;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.pharmacy.management.system.domain.enums.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public class Order {
    private Integer id;

    @Min(value = 1, message = "Customer ID is required")
    private Integer customerId;

    @NotBlank(message = "Customer phone is required")
    private String customerPhone;

    private LocalDateTime orderDateTime;
    private LocalDateTime returnDateTime;

    @PositiveOrZero(message = "Total amount must be zero or positive")
    private Float totalAmount;

    private Float refundAmount = 0f;

    @NotNull(message = "Status is required")
    private OrderStatus status;

    @Min(value = 1, message = "Seller ID is required")
    private Integer sellerId;

    public Order() {}

    public Order(Integer id, Integer customerId, String customerPhone, LocalDateTime orderDateTime,
                 LocalDateTime returnDateTime, Float totalAmount, Float refundAmount,
                 OrderStatus status, Integer sellerId) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
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

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Float refundAmount) {
        this.refundAmount = refundAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
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
