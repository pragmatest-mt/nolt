package com.pragmatest.nolt.restaurant_orders.services;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.restaurant_orders.common.enums.OrderState;
import com.pragmatest.nolt.restaurant_orders.data.entities.RestaurantOrderEntity;
import com.pragmatest.nolt.restaurant_orders.data.repositories.RestaurantsOrderRepository;
import com.pragmatest.nolt.restaurant_orders.services.models.Order;
import com.pragmatest.nolt.restaurant_orders.services.models.OrderItem;
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

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class RestaurantsOrdersServiceTests {

    @Autowired
    RestaurantsOrderService restaurantsOrderService;

    @MockBean
    RestaurantsOrderRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testAcceptValidOrder() {
        // Arrange
        String orderId = UUID.randomUUID().toString();

        List<OrderItem> orderItems = List.of(new OrderItem("burger", 1, "extra cheese"),
                new OrderItem("pizza margherita", 2, ""));

        Order foundOrder = new Order(orderId, orderItems, OrderState.SUBMITTED);
        RestaurantOrderEntity submittedOrderEntity = modelMapper.map(foundOrder, RestaurantOrderEntity.class);
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
}

class RestaurantOrderEntityMatcher implements ArgumentMatcher<RestaurantOrderEntity> {

    private RestaurantOrderEntity left;
    private RestaurantOrderEntity match;

    public RestaurantOrderEntityMatcher(RestaurantOrderEntity left) {
        this.left = left;
    }

    @Override
    public boolean matches(RestaurantOrderEntity right) {
        boolean isMatch = left != null && right != null &&
                left.getOrderId().equals(right.getOrderId()) &&
                DeepEquals.deepEquals(left.getOrderItems(), right.getOrderItems()) &&
                isValidUUID(right.getOrderId());

        if (isMatch) {
            match = right;
        }

        return isMatch;
    }

    public RestaurantOrderEntity getMatch() {
        return match;
    }

    private boolean isValidUUID(String id) {

        if (id == null) return false;

        try {
            UUID uuid = UUID.fromString(id);
            return id.equals(uuid.toString());
        } catch(IllegalArgumentException e) {
            return false;
        }
    }
}