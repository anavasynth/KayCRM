package com.kaycrm.kaycrm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 
  @author  Anavasynth
  @project  IntelliJ IDEA
  @class  ModelMapperConfig
  version 1.0.0
  @since 31.03.2025 - 16.39
*/

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
