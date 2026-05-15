package com.pharmacy.management.system.repository;

import com.pharmacy.management.system.domain.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailsRepository {
    int save(OrderDetails orderDetails);
    Optional<OrderDetails> findById(int id);
    List<OrderDetails> findByOrderId(int orderId);
    List<OrderDetails> findAll();
    List<OrderDetails> findAllByPage(int pageNo, int pageSize);
    int count();
    int update(OrderDetails orderDetails);
    int delete(int id);
    int deleteByOrderId(int orderId);
    int delete(OrderDetails orderDetails);
}
