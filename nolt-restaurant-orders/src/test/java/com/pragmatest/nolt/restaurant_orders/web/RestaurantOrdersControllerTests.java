package com.pragmatest.nolt.restaurant_orders.web;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.restaurant_orders.common.enums.OrderState;
import com.pragmatest.nolt.restaurant_orders.services.RestaurantsOrderService;
import com.pragmatest.nolt.restaurant_orders.services.models.Order;
import com.pragmatest.nolt.restaurant_orders.services.models.OrderItem;
import com.pragmatest.nolt.restaurant_orders.web.controllers.RestaurantOrdersController;
import com.pragmatest.nolt.restaurant_orders.web.responses.AcceptOrderResponse;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class RestaurantOrdersControllerTests {

    @Autowired
    RestaurantOrdersController restaurantOrdersController;

    @MockBean
    RestaurantsOrderService restaurantsOrderServiceMock;

    @Autowired
    ModelMapper mapper;

    @Test
    public void accept_AcceptExistentOrderId_OrderAccepted() {
        // Arrange
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId,
                List.of(new OrderItem("burger", 1, "extra cheese"),
                        new OrderItem("pizza margherita", 2, "")),
                OrderState.ACCEPTED);

        when(restaurantsOrderServiceMock.acceptOrder(orderId)).thenReturn(order);

        // Act
        AcceptOrderResponse actualOrderResponse = restaurantOrdersController.accept(orderId);

        // Assert
        assertNotNull(actualOrderResponse, "Order Response is null");

        AcceptOrderResponse expectedOrderResponse = mapper.map(order, AcceptOrderResponse.class);


        DeepEquals.deepEquals(expectedOrderResponse, actualOrderResponse);

        verify(restaurantsOrderServiceMock, times(1)).acceptOrder(orderId);
    }

    @Test
    public void accept_AcceptNonExistentOrderId_ExceptionThrown() {
        // Arrange
        String orderOrderId = UUID.randomUUID().toString();
        String expectedExceptionMessage = "Order not found";

        when(restaurantsOrderServiceMock.acceptOrder(orderOrderId)).thenReturn(null);

        // Act
        Exception thrownException = assertThrows(ResponseStatusException.class, () -> {
            restaurantOrdersController.accept(orderOrderId);
        });

        // Assert
        String actualExceptionMessage = thrownException.getMessage();
        assertTrue(actualExceptionMessage.contains(expectedExceptionMessage));

        verify(restaurantsOrderServiceMock, times(1)).acceptOrder(orderOrderId);
    }
}
