package com.pharmacy.management.system.service.implementation;

import com.pharmacy.management.system.domain.OrderDetails;
import com.pharmacy.management.system.repository.implementation.OrderDetailsRepository;
import com.pharmacy.management.system.service.IOrderDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailsService implements IOrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        System.out.println("[OrderDetailsService] saveOrderDetails called for orderId: " + orderDetails.getOrderId());
        orderDetailsRepository.save(orderDetails);
        return orderDetails;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDetails> findOrderDetailsById(int id) {
        System.out.println("[OrderDetailsService] findOrderDetailsById called for id: " + id);
        return orderDetailsRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetails> findOrderDetailsByOrderId(int orderId) {
        System.out.println("[OrderDetailsService] findOrderDetailsByOrderId called for orderId: " + orderId);
        return orderDetailsRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetails> findAllOrderDetails() {
        System.out.println("[OrderDetailsService] findAllOrderDetails called");
        return orderDetailsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetails> findAllOrderDetailsByPage(int pageNo, int pageSize) {
        System.out.println("[OrderDetailsService] findAllOrderDetailsByPage called - page: " + pageNo + ", size: " + pageSize);
        return orderDetailsRepository.findAllByPage(pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public int countOrderDetails() {
        System.out.println("[OrderDetailsService] countOrderDetails called");
        return orderDetailsRepository.count();
    }

    @Override
    public OrderDetails updateOrderDetails(OrderDetails orderDetails) {
        System.out.println("[OrderDetailsService] updateOrderDetails called for id: " + orderDetails.getId());
        orderDetailsRepository.update(orderDetails);
        return orderDetails;
    }

    @Override
    public void deleteOrderDetails(int id) {
        System.out.println("[OrderDetailsService] deleteOrderDetails called for id: " + id);
        orderDetailsRepository.delete(id);
    }

    @Override
    public void deleteOrderDetailsByOrderId(int orderId) {
        System.out.println("[OrderDetailsService] deleteOrderDetailsByOrderId called for orderId: " + orderId);
        orderDetailsRepository.deleteByOrderId(orderId);
    }
}
