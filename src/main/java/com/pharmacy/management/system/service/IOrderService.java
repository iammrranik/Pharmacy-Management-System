package com.pharmacy.management.system.service;

import com.pharmacy.management.system.domain.Order;
import com.pharmacy.management.system.domain.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Order saveOrder(Order order);
    Optional<Order> findOrderById(int id);
    List<Order> findOrdersByCustomerPhone(String phone);
    List<Order> findOrdersByStatus(OrderStatus status);
    List<Order> findAllOrders();
    List<Order> findAllOrdersByPage(int pageNo, int pageSize);
    int countOrders();
    Order updateOrder(Order order);
    void deleteOrder(int id);
    Order updateOrderStatus(int id, OrderStatus status);
    Order returnMedicine(int id);
}
