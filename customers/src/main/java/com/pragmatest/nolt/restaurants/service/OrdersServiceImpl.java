package com.pragmatest.nolt.restaurants.service;

import com.pragmatest.nolt.restaurants.messaging.events.OrderSubmittedEvent;
import com.pragmatest.nolt.restaurants.messaging.producers.OrderSubmittedProducer;
import com.pragmatest.nolt.restaurants.data.entities.OrderEntity;
import com.pragmatest.nolt.restaurants.data.respositories.OrdersRepository;
import com.pragmatest.nolt.restaurants.enums.OrderState;
import com.pragmatest.nolt.restaurants.service.models.Order;
import com.pragmatest.nolt.restaurants.web.responses.OrderResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Transactional
@Service
public class OrdersServiceImpl implements  OrdersService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderSubmittedProducer orderSubmittedProducer;

    @Override
    public List<Order> getOrders() {
        List<OrderEntity> orderEntities = ordersRepository.findAll();

        Type returnType = new TypeToken<List<OrderResponse>>() {
        }.getType();

        List<Order> orders = modelMapper.map(orderEntities, returnType);

        return orders;
    }

    @Override
    public Order submitOrder(Order order) {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        orderEntity = ordersRepository.save(orderEntity);

        OrderSubmittedEvent orderSubmittedEvent = modelMapper.map(orderEntity, OrderSubmittedEvent.class);
        orderSubmittedProducer.send(orderSubmittedEvent);

        order = modelMapper.map(orderEntity, Order.class);

        return order;
    }

    @Override
    public Order acceptOrder(Order order) {

        OrderEntity orderEntity = ordersRepository.getById(order.getOrderId());
        orderEntity.setState(OrderState.ACCEPTED);

        orderEntity = ordersRepository.save(orderEntity);

        order = modelMapper.map(orderEntity, Order.class);

        return order;
    }

}