package com.pharmacy.management.system.service.implementation;

import com.pharmacy.management.system.domain.Medicine;
import com.pharmacy.management.system.domain.Order;
import com.pharmacy.management.system.domain.OrderDetails;
import com.pharmacy.management.system.domain.enums.OrderStatus;
import com.pharmacy.management.system.repository.implementation.MedicineRepository;
import com.pharmacy.management.system.repository.implementation.OrderDetailsRepository;
import com.pharmacy.management.system.repository.implementation.OrderRepository;
import com.pharmacy.management.system.service.IOrderDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService implements IOrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final MedicineRepository medicineRepository;
    private final OrderRepository orderRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository,
                               MedicineRepository medicineRepository,
                               OrderRepository orderRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.medicineRepository = medicineRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        System.out.println("[OrderDetailsService] saveOrderDetails called for orderId: " + orderDetails.getOrderId());
        orderDetailsRepository.save(orderDetails);

        Medicine medicine = medicineRepository.findById(orderDetails.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + orderDetails.getMedicineId()));
        medicine.setAvailableQuantity(medicine.getAvailableQuantity() - orderDetails.getQuantity());
        medicineRepository.update(medicine);
        System.out.println("[OrderDetailsService] Deducted " + orderDetails.getQuantity() + " from medicine " + orderDetails.getMedicineId()
                + ", new quantity: " + medicine.getAvailableQuantity());

        Order order = orderRepository.findById(orderDetails.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderDetails.getOrderId()));
        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.update(order);
        System.out.println("[OrderDetailsService] Order " + order.getId() + " status set to COMPLETED");

        return orderDetails;
    }

    @Override
    public Optional<OrderDetails> findOrderDetailsById(int id) {
        System.out.println("[OrderDetailsService] findOrderDetailsById called for id: " + id);
        return orderDetailsRepository.findById(id);
    }

    @Override
    public List<OrderDetails> findOrderDetailsByOrderId(int orderId) {
        System.out.println("[OrderDetailsService] findOrderDetailsByOrderId called for orderId: " + orderId);
        return orderDetailsRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderDetails> findAllOrderDetails() {
        System.out.println("[OrderDetailsService] findAllOrderDetails called");
        return orderDetailsRepository.findAll();
    }

    @Override
    public List<OrderDetails> findAllOrderDetailsByPage(int pageNo, int pageSize) {
        System.out.println("[OrderDetailsService] findAllOrderDetailsByPage called - page: " + pageNo + ", size: " + pageSize);
        return orderDetailsRepository.findAllByPage(pageNo, pageSize);
    }

    @Override
    public int countOrderDetails() {
        System.out.println("[OrderDetailsService] countOrderDetails called");
        return orderDetailsRepository.count();
    }

    @Override
    @Transactional
    public OrderDetails updateOrderDetails(OrderDetails orderDetails) {
        System.out.println("[OrderDetailsService] updateOrderDetails called for id: " + orderDetails.getId());
        orderDetailsRepository.update(orderDetails);
        return orderDetails;
    }

    @Override
    @Transactional
    public void deleteOrderDetails(int id) {
        System.out.println("[OrderDetailsService] deleteOrderDetails called for id: " + id);
        orderDetailsRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteOrderDetailsByOrderId(int orderId) {
        System.out.println("[OrderDetailsService] deleteOrderDetailsByOrderId called for orderId: " + orderId);
        orderDetailsRepository.deleteByOrderId(orderId);
    }
}
