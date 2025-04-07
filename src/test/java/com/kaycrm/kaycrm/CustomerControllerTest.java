package com.kaycrm.kaycrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaycrm.kaycrm.dto.CustomerDTO;
import com.kaycrm.kaycrm.model.Customer;
import com.kaycrm.kaycrm.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

/* 
  @author  Anavasynth
  @project  IntelliJ IDEA
  @class  CustomerControllerTest
  version 1.0.0
  @since 07.04.2025 - 15.09
*/

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer = new Customer("Vitya", "vitya@gmail.com", "0995656456");
        customerRepository.save(customer);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Vitya"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer("Vitya", "vitya@gmail.com", "0995656456");
        Customer savedCustomer = customerRepository.save(customer);

        mockMvc.perform(get("/customers/" + savedCustomer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Vitya"));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Vitya");
        customerDTO.setEmail("vitya@gmail.com");
        customerDTO.setPhone("0995656456");

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Vitya"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = new Customer("Vitya", "vitya@gmail.com", "0995656456");
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO updatedCustomerDTO = new CustomerDTO();
        updatedCustomerDTO.setName("Vitya");
        updatedCustomerDTO.setEmail("vitya@gmail.com");
        updatedCustomerDTO.setPhone("987654321");

        mockMvc.perform(put("/customers/" + savedCustomer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCustomerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Vitya"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Customer customer = new Customer("Vitya", "vitya@gmail.com", "0995656456");
        Customer savedCustomer = customerRepository.save(customer);

        mockMvc.perform(delete("/customers/" + savedCustomer.getId()))
                .andExpect(status().isNoContent());

        List<Customer> customers = customerRepository.findAll();
        assert customers.isEmpty();
    }
}
