package com.kaycrm.kaycrm.service;

import com.kaycrm.kaycrm.repository.OrderRepository;
import com.kaycrm.kaycrm.model.Order;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Отримати всі замовлення
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Отримати замовлення за ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Зберегти нове або оновлене замовлення
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Видалити замовлення
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
