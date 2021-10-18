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
import org.springframework.http.HttpStatus;
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
    public void accept_AcceptExistentOrderId_ReturnsAcceptedOrder() {
        // Arrange
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId,
                List.of(new OrderItem("burger", 1, "extra cheese"),
                        new OrderItem("pizza margherita", 2, "")),
                OrderState.ACCEPTED);

        AcceptOrderResponse expectedOrderResponse = mapper.map(order, AcceptOrderResponse.class);

        when(restaurantsOrderServiceMock.acceptOrder(orderId)).thenReturn(order);

        // Act
        AcceptOrderResponse actualOrderResponse = restaurantOrdersController.accept(orderId);

        // Assert
        assertNotNull(actualOrderResponse, "Order Response is null");

        DeepEquals.deepEquals(expectedOrderResponse, actualOrderResponse);

        verify(restaurantsOrderServiceMock, times(1)).acceptOrder(orderId);
    }

    @Test
    public void accept_AcceptNonExistentOrderId_ThrowsException() {
        // Arrange
        String orderOrderId = UUID.randomUUID().toString();

        when(restaurantsOrderServiceMock.acceptOrder(orderOrderId)).thenReturn(null);

        // Act
        ResponseStatusException thrownException = assertThrows(ResponseStatusException.class, () -> {
            restaurantOrdersController.accept(orderOrderId);
        });

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, thrownException.getStatus());

        verify(restaurantsOrderServiceMock, times(1)).acceptOrder(orderOrderId);
    }
}
