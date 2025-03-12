package com.kaycrm.kaycrm.repository;


import com.kaycrm.kaycrm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* 
  @author  Anavasynth
  @project  IntelliJ IDEA
  @class  CustomerRepository
  version 1.0.0
  @since 06.03.2025 - 23.41
*/

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
