package com.pragmatest.nolt.customer_orders.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapperFactory() {
        return new ModelMapper();
    }

}
