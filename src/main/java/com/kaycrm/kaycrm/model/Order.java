package com.kaycrm.kaycrm.model;

import jakarta.persistence.*;
import lombok.*;

/* 
  @author  Anavasynth
  @project  IntelliJ IDEA
  @class  Order
  version 1.0.0
  @since 06.03.2025 - 23.40
*/

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
