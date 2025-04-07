package com.kaycrm.kaycrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaycrm.kaycrm.dto.OrderDTO;
import com.kaycrm.kaycrm.model.Customer;
import com.kaycrm.kaycrm.model.Order;
import com.kaycrm.kaycrm.repository.CustomerRepository;
import com.kaycrm.kaycrm.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
  @author  Anavasynth
  @project  IntelliJ IDEA
  @class  OrderControllerTest
  version 1.0.0
  @since 07.04.2025 - 17.03
*/

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", "123456789");
        customerRepository.save(customer);

        Order order = new Order("Sample Product", 100.00, customer);
        orderRepository.save(order);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].product").value("Sample Product"));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", "123456789");
        customerRepository.save(customer);

        Order order = new Order("Sample Product", 100.00, customer);
        Order savedOrder = orderRepository.save(order);

        mockMvc.perform(get("/orders/" + savedOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product").value("Sample Product"));
    }

    @Test
    public void testCreateOrder() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", "123456789");
        Customer savedCustomer = customerRepository.save(customer);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProduct("Sample Product");
        orderDTO.setPrice(100.00);
        orderDTO.setCustomerId(savedCustomer.getId());

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product").value("Sample Product"));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", "123456789");
        customerRepository.save(customer);

        Order order = new Order("Sample Product", 100.00, customer);
        Order savedOrder = orderRepository.save(order);

        OrderDTO updatedOrderDTO = new OrderDTO();
        updatedOrderDTO.setProduct("Updated Product");
        updatedOrderDTO.setPrice(200.00);
        updatedOrderDTO.setCustomerId(customer.getId());

        mockMvc.perform(put("/orders/" + savedOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedOrderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product").value("Updated Product"));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", "123456789");
        customerRepository.save(customer);

        Order order = new Order("Sample Product", 100.00, customer);
        Order savedOrder = orderRepository.save(order);

        mockMvc.perform(delete("/orders/" + savedOrder.getId()))
                .andExpect(status().isNoContent());

        List<Order> orders = orderRepository.findAll();
        assert orders.isEmpty();
    }
}
