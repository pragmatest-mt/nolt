package com.pragmatest.nolt.restaurants.data.respositories;

import com.pragmatest.nolt.restaurants.data.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrderEntity, String> {
    
}