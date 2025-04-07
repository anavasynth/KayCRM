package com.kaycrm.kaycrm.mapper;

import com.kaycrm.kaycrm.dto.OrderDTO;
import com.kaycrm.kaycrm.model.Order;
import com.kaycrm.kaycrm.model.Customer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "customer.id", target = "customerId")
    OrderDTO toDto(Order order);

    @Mapping(target = "customer", ignore = true)
    Order toEntity(OrderDTO orderDTO);

    @AfterMapping
    default void setCustomer(@MappingTarget Order order, OrderDTO orderDTO) {
        if (orderDTO.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(orderDTO.getCustomerId());
            order.setCustomer(customer);
        }
    }
}