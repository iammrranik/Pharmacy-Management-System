package com.pharmacy.management.system.service;

import com.pharmacy.management.system.domain.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailsService {
    OrderDetails saveOrderDetails(OrderDetails orderDetails);
    Optional<OrderDetails> findOrderDetailsById(int id);
    List<OrderDetails> findOrderDetailsByOrderId(int orderId);
    List<OrderDetails> findAllOrderDetails();
    List<OrderDetails> findAllOrderDetailsByPage(int pageNo, int pageSize);
    int countOrderDetails();
    OrderDetails updateOrderDetails(OrderDetails orderDetails);
    void deleteOrderDetails(int id);
    void deleteOrderDetailsByOrderId(int orderId);
}
