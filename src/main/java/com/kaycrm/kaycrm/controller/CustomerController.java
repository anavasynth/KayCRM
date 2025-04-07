package com.kaycrm.kaycrm.controller;

import com.kaycrm.kaycrm.dto.CustomerDTO;
import com.kaycrm.kaycrm.exception.ResourceNotFoundException;
import com.kaycrm.kaycrm.mapper.CustomerMapper;
import com.kaycrm.kaycrm.model.Customer;
import com.kaycrm.kaycrm.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found:)"));
        return ResponseEntity.ok(CustomerMapper.INSTANCE.toDto(customer));
    }

    @PostMapping
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer existingCustomer = customerService.getCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDTO);
        customer.setId(existingCustomer.getId());
        return ResponseEntity.ok(customerService.saveCustomer(CustomerMapper.INSTANCE.toDto(customer)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}