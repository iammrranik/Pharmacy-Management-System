package com.pharmacy.management.system.service.implementation;

import com.pharmacy.management.system.domain.Medicine;
import com.pharmacy.management.system.domain.Order;
import com.pharmacy.management.system.domain.OrderDetails;
import com.pharmacy.management.system.domain.enums.OrderStatus;
import com.pharmacy.management.system.repository.implementation.MedicineRepository;
import com.pharmacy.management.system.repository.implementation.OrderDetailsRepository;
import com.pharmacy.management.system.repository.implementation.OrderRepository;
import com.pharmacy.management.system.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final MedicineRepository medicineRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderDetailsRepository orderDetailsRepository,
                        MedicineRepository medicineRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    @Transactional
    public Order createOrder(Order order, List<OrderDetails> orderDetails) {
        System.out.println("[OrderService] createOrder called for customer: " + order.getCustomerPhone());
        orderRepository.save(order);
        for (OrderDetails detail : orderDetails) {
            detail.setOrderId(order.getId());
            orderDetailsRepository.save(detail);
        }
        return order;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        System.out.println("[OrderService] saveOrder called for customer: " + order.getCustomerPhone());
        orderRepository.save(order);
        return order;
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        System.out.println("[OrderService] findOrderById called for id: " + id);
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findOrdersByCustomerPhone(String phone) {
        System.out.println("[OrderService] findOrdersByCustomerPhone called for phone: " + phone);
        return orderRepository.findByCustomerPhone(phone);
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus status) {
        System.out.println("[OrderService] findOrdersByStatus called for status: " + status);
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> findAllOrders() {
        System.out.println("[OrderService] findAllOrders called");
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllOrdersByPage(int pageNo, int pageSize) {
        System.out.println("[OrderService] findAllOrdersByPage called - page: " + pageNo + ", size: " + pageSize);
        return orderRepository.findAllByPage(pageNo, pageSize);
    }

    @Override
    public int countOrders() {
        System.out.println("[OrderService] countOrders called");
        return orderRepository.count();
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        System.out.println("[OrderService] updateOrder called for id: " + order.getId());
        orderRepository.update(order);
        return order;
    }

    @Override
    @Transactional
    public void deleteOrder(int id) {
        System.out.println("[OrderService] deleteOrder called for id: " + id);
        orderDetailsRepository.deleteByOrderId(id);
        orderRepository.delete(id);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(int id, OrderStatus status) {
        System.out.println("[OrderService] updateOrderStatus called for id: " + id + ", status: " + status);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        order.setStatus(status);
        orderRepository.update(order);
        return order;
    }

    @Override
    @Transactional
    public Order returnMedicine(int id) {
        System.out.println("[OrderService] returnMedicine called for id: " + id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (order.getReturnDateTime() != null) {
            throw new RuntimeException("Order has already been returned");
        }

        LocalDateTime now = LocalDateTime.now();
        float refundPercentage = 0f;

        if (now.isAfter(order.getOrderDateTime())) {
            long hours = Duration.between(order.getOrderDateTime(), now).toHours();
            if (hours < 3) {
                refundPercentage = 1f;
            } else if (hours < 6) {
                refundPercentage = 0.90f;
            } else if (hours < 12) {
                refundPercentage = 0.75f;
            } else if (hours < 24) {
                refundPercentage = 0.50f;
            } else if (hours < 36) {
                refundPercentage = 0.25f;
            } else if (hours < 72) {
                refundPercentage = 0.10f;
            }
        }

        float refundAmount = order.getTotalAmount() * refundPercentage;
        order.setRefundAmount(refundAmount);
        order.setTotalAmount(order.getTotalAmount() - refundAmount);
        order.setStatus(OrderStatus.REFUNDED);
        order.setReturnDateTime(now);
        orderRepository.update(order);

        List<OrderDetails> details = orderDetailsRepository.findByOrderId(id);
        for (OrderDetails detail : details) {
            Medicine medicine = medicineRepository.findById(detail.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + detail.getMedicineId()));
            medicine.setAvailableQuantity(medicine.getAvailableQuantity() + detail.getQuantity());
            medicineRepository.update(medicine);
        }

        return order;
    }
}
