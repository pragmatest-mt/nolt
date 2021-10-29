package com.pragmatest.nolt.restaurant_orders.data.repositories;

import com.pragmatest.nolt.restaurant_orders.data.entities.RestaurantOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantsOrderRepository extends JpaRepository<RestaurantOrderEntity, String> {

}
