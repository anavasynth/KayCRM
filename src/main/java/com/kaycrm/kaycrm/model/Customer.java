package com.kaycrm.kaycrm.model;

/* 
  @author  Anavasynth
  @project  IntelliJ IDEA
  @class  Customer
  version 1.0.0
  @since 06.03.2025 - 23.35
*/

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
}
