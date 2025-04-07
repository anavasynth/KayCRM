package com.kaycrm.kaycrm.config;

import com.kaycrm.kaycrm.mapper.CustomerMapper;
import com.kaycrm.kaycrm.mapper.OrderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CustomerMapper customerMapper() {
        return CustomerMapper.INSTANCE;
    }

    @Bean
    public OrderMapper orderMapper() {
        return OrderMapper.INSTANCE;
    }
}