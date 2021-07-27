package com.pragmatest.nolt.customers.services;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.customers.data.entities.OrderEntity;
import com.pragmatest.nolt.customers.data.entities.OrderItemEntity;
import com.pragmatest.nolt.customers.data.respositories.OrdersRepository;
import com.pragmatest.nolt.customers.matchers.OrderEntityMatcher;
import com.pragmatest.nolt.customers.messaging.producers.OrderSubmittedProducer;
import com.pragmatest.nolt.customers.service.OrdersService;
import com.pragmatest.nolt.customers.service.models.Order;
import com.pragmatest.nolt.customers.service.models.OrderItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class OrderEntityServiceTest {

    @Autowired
    OrdersService ordersService;

    @MockBean
    OrdersRepository ordersMockRepository;

    @MockBean
    OrderSubmittedProducer orderSubmittedMockProducer;

    @Test
    void testSubmitOrderValidOrder(){
        //Arrange
        String userId = "1";
        String orderId = UUID.randomUUID().toString();

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem("1", 1, "Extra coleslaw"));
        orderItemList.add(new OrderItem("2", 2 ));

        List<OrderItemEntity> orderItemEntityList = new ArrayList<>();
        orderItemEntityList.add(new OrderItemEntity("1", 1, "Extra coleslaw"));
        orderItemEntityList.add(new OrderItemEntity("2", 2));

        Order submitOrderInput = new Order(userId, orderItemList);

        OrderEntity testOrderEntity = new OrderEntity(userId);
        testOrderEntity.setOrderItems(orderItemEntityList);

        when(ordersMockRepository.save(argThat(new OrderEntityMatcher(testOrderEntity)))).thenReturn(testOrderEntity);

        Order expectedOrderServiceOutput = new Order(userId, orderId, orderItemList);

        //Act
        Order actualOrderServiceOutput = ordersService.submitOrder(submitOrderInput);

        //Assert
        assertTrue(DeepEquals.deepEquals(actualOrderServiceOutput, expectedOrderServiceOutput));
        verify(ordersMockRepository, times(1))
                .save(argThat(new OrderEntityMatcher(testOrderEntity)));
    }
}
