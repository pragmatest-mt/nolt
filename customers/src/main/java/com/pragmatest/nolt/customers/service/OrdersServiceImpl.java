package com.pragmatest.nolt.customers.service;

import com.pragmatest.nolt.customers.messaging.events.OrderSubmittedEvent;
import com.pragmatest.nolt.customers.messaging.producers.OrderSubmittedProducer;
import com.pragmatest.nolt.customers.data.entities.OrderEntity;
import com.pragmatest.nolt.customers.data.respositories.OrdersRepository;
import com.pragmatest.nolt.customers.enums.OrderState;
import com.pragmatest.nolt.customers.service.models.Order;
import com.pragmatest.nolt.customers.web.responses.OrderResponse;
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