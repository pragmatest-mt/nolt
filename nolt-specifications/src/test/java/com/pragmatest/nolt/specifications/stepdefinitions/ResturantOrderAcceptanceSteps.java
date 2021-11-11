package com.pragmatest.nolt.specifications.stepdefinitions;

import com.pragmatest.nolt.specifications.common.models.restaurants.RestaurantAcceptOrderRequest;
import com.pragmatest.nolt.specifications.common.models.restaurants.RestaurantAcceptOrderResponse;
import com.pragmatest.nolt.specifications.common.services.restaurants.ResturantsOrderService;
import com.pragmatest.nolt.specifications.common.state.Order;
import io.cucumber.java.en.When;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResturantOrderAcceptanceSteps {

    @Autowired
    ResturantsOrderService resturantOrderService;

    @Autowired
    Order order;

    @When("the restaurant accepts the order")
    public void theRestaurantAcceptsTheOrder() {
        String orderId = order.getOrderId();
        String restaurantId = UUID.randomUUID().toString();

        DateTime estimatedDeliveryTime = DateTime.now().plusHours(2);
        RestaurantAcceptOrderRequest request = new RestaurantAcceptOrderRequest();

        ResponseEntity<RestaurantAcceptOrderResponse> acceptOrderResponseEntity = resturantOrderService.AcceptRestaurantOrder(orderId, restaurantId, request);

        order.setExpectedDeliveryDate(estimatedDeliveryTime);
    }
}
