package com.kaycrm.kaycrm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Name is empty")
    private String name;

    @NotNull
    @Email(message = "Email not valid")
    private String email;

    private String phone;
}
