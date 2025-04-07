package com.kaycrm.kaycrm.service;

import com.kaycrm.kaycrm.dto.OrderDTO;
import com.kaycrm.kaycrm.exception.ResourceNotFoundException;
import com.kaycrm.kaycrm.mapper.OrderMapper;
import com.kaycrm.kaycrm.model.Customer;
import com.kaycrm.kaycrm.model.Order;
import com.kaycrm.kaycrm.repository.CustomerRepository;
import com.kaycrm.kaycrm.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        order.setCustomer(customer);

        return OrderMapper.INSTANCE.toDto(orderRepository.save(order));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
