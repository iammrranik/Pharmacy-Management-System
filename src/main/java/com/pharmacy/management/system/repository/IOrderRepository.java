package com.pharmacy.management.system.repository;

import com.pharmacy.management.system.domain.Order;
import com.pharmacy.management.system.domain.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {
    int save(Order order);
    int[] saveAll(List<Order> orders);
    Optional<Order> findById(int id);
    List<Order> findByCustomerPhone(String phone);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findAll();
    List<Order> findAllByPage(int pageNo, int pageSize);
    int count();
    int update(Order order);
    int delete(int id);
    int delete(Order order);
}
