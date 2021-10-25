package com.pragmatest.nolt.restaurant_orders.services;

import com.pragmatest.nolt.restaurant_orders.common.enums.OrderState;
import com.pragmatest.nolt.restaurant_orders.data.entities.RestaurantOrderEntity;
import com.pragmatest.nolt.restaurant_orders.data.repositories.RestaurantsOrderRepository;
import com.pragmatest.nolt.restaurant_orders.services.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantOrdersService
{
    @Autowired
    RestaurantsOrderRepository repository;

    @Autowired
    ModelMapper mapper;

    public Order acceptOrder(String orderId){
        Optional<RestaurantOrderEntity> restaurantOrderEntity = repository.findById(orderId);

        if (restaurantOrderEntity.isEmpty()) {
            return null;
        }

        restaurantOrderEntity.get().setOrderState(OrderState.ACCEPTED);
        RestaurantOrderEntity updatedOrderEntity = repository.save(restaurantOrderEntity.get());

        Order updatedOrder = mapper.map(updatedOrderEntity, Order.class);

        return updatedOrder;
    }
}
