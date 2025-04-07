package com.kaycrm.kaycrm.mapper;

import com.kaycrm.kaycrm.dto.CustomerDTO;
import com.kaycrm.kaycrm.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDto(Customer customer);

    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);
}