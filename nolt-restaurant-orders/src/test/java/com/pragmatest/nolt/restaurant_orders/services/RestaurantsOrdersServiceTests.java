package com.pragmatest.nolt.restaurant_orders.services;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.restaurant_orders.common.enums.OrderState;
import com.pragmatest.nolt.restaurant_orders.data.entities.OrderItem;
import com.pragmatest.nolt.restaurant_orders.data.entities.RestaurantOrderEntity;
import com.pragmatest.nolt.restaurant_orders.data.repositories.RestaurantsOrderRepository;
import com.pragmatest.nolt.restaurant_orders.services.models.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class RestaurantsOrdersServiceTests {

    @Autowired
    RestaurantOrdersService restaurantsOrderService;

    @MockBean
    RestaurantsOrderRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void acceptOrder_AcceptExistentOrderId_ReturnsUpdatedOrder() {
        // Arrange
        String orderId = UUID.randomUUID().toString();

        List<OrderItem> entityOrderItems = List.of(new OrderItem("burger", 1, "extra cheese"),
                new OrderItem("pizza margherita", 2, ""));

        List<com.pragmatest.nolt.restaurant_orders.services.models.OrderItem> orderItems
                = List.of(new com.pragmatest.nolt.restaurant_orders.services.models.OrderItem(
                        "burger",
                        1,
                        "extra cheese"),
                new com.pragmatest.nolt.restaurant_orders.services.models.OrderItem(
                        "pizza margherita",
                        2,
                        ""));

        RestaurantOrderEntity submittedOrderEntity
                = new RestaurantOrderEntity(entityOrderItems, orderId,OrderState.SUBMITTED);
        when(repository.findById(orderId)).thenReturn(Optional.of(submittedOrderEntity));

        Order expectedOrder = new Order(orderId, orderItems, OrderState.ACCEPTED);
        RestaurantOrderEntity acceptedOrderEntity = modelMapper.map(expectedOrder, RestaurantOrderEntity.class);
        RestaurantOrderEntityMatcher acceptedOrderEntityMatcher = new RestaurantOrderEntityMatcher(acceptedOrderEntity);
        when(repository.save(argThat(acceptedOrderEntityMatcher))).thenReturn(acceptedOrderEntity);

        // Act
        Order actualOrder = restaurantsOrderService.acceptOrder(orderId);

        // Assert
        DeepEquals.deepEquals(expectedOrder, actualOrder);

        verify(repository, times(1)).findById(orderId);
        verify(repository, times(1)).save(argThat(acceptedOrderEntityMatcher));
    }

    @Test
    public void acceptOrder_AcceptNonExistentOrderId_ReturnsNull() {
        // Arrange
        String orderId = UUID.randomUUID().toString();

        List<OrderItem> entityOrderItems = List.of(new OrderItem("burger", 1, "extra cheese"),
                new OrderItem("pizza margherita", 2, ""));

        List<com.pragmatest.nolt.restaurant_orders.services.models.OrderItem> orderItems
                = List.of(new com.pragmatest.nolt.restaurant_orders.services.models.OrderItem(
                        "burger",
                        1,
                        "extra cheese"),
                new com.pragmatest.nolt.restaurant_orders.services.models.OrderItem(
                        "pizza margherita",
                        2,
                        ""));

        RestaurantOrderEntity submittedOrderEntity
                = new RestaurantOrderEntity(entityOrderItems, orderId,OrderState.SUBMITTED);
        when(repository.findById(orderId)).thenReturn(Optional.empty());

        Order expectedOrder = new Order(orderId, orderItems, OrderState.ACCEPTED);
        RestaurantOrderEntity acceptedOrderEntity = modelMapper.map(expectedOrder, RestaurantOrderEntity.class);
        RestaurantOrderEntityMatcher acceptedOrderEntityMatcher = new RestaurantOrderEntityMatcher(acceptedOrderEntity);

        // Act
        Order actualOrder = restaurantsOrderService.acceptOrder(orderId);

        // Assert

        assertNull(actualOrder);

        verify(repository, times(1)).findById(orderId);
        verify(repository, times(0)).save(argThat(acceptedOrderEntityMatcher));
    }
}


class RestaurantOrderEntityMatcher implements ArgumentMatcher<RestaurantOrderEntity> {

    private RestaurantOrderEntity left;

    public RestaurantOrderEntityMatcher(RestaurantOrderEntity left) {
        this.left = left;
    }

    @Override
    public boolean matches(RestaurantOrderEntity right) {
        boolean isMatch = left != null && right != null &&
                left.getOrderId().equals(right.getOrderId()) &&
                DeepEquals.deepEquals(left.getOrderItems(), right.getOrderItems());

        return isMatch;
    }
}