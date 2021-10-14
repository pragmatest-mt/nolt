package com.pragmatest.nolt.restaurant_orders;

import com.pragmatest.nolt.restaurant_orders.data.repositories.RestaurantsOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestaurantsOrderApplication {

    @Autowired
    RestaurantsOrderRepository repository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RestaurantsOrderApplication.class, args);
    }
}