package com.kaycrm.kaycrm.service;

import com.kaycrm.kaycrm.repository.CustomerRepository;
import com.kaycrm.kaycrm.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/* 
  @author  Anavasynth
  @project  IntelliJ IDEA
  @class  CustomerService
  version 1.0.0
  @since 06.03.2025 - 23.41
*/

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
