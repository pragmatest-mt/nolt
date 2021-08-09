package com.pragmatest.nolt.e2e.tests.stepdefinitions;

import com.pragmatest.nolt.e2e.tests.common.models.OrderItem;
import com.pragmatest.nolt.e2e.tests.common.models.restaurants.SubmitCustomerOrderRequest;
import com.pragmatest.nolt.e2e.tests.common.models.restaurants.SubmitCustomerOrderResponse;
import com.pragmatest.nolt.e2e.tests.common.services.customers.CustomerOrderService;
import com.pragmatest.nolt.e2e.tests.common.state.Order;
import com.pragmatest.nolt.e2e.tests.common.state.OrderState;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

public class CustomerOrderSubmissionSteps {

    @Autowired
    CustomerOrderService customerService;

    @Autowired
    Order order;

    @Given("a customer submits an order to a restaurant")
    public void aCustomerSubmitsAnOrderToARestaurant() {
        String customerId = "test-".concat(UUID.randomUUID().toString());
        order.setCustomerId(customerId);

        SubmitCustomerOrderRequest orderRequest = new SubmitCustomerOrderRequest();
        orderRequest.setOrderItems(Collections.singletonList(new OrderItem("test-".concat(UUID.randomUUID().toString()), 1, "No notes.")));

        ResponseEntity<SubmitCustomerOrderResponse> customerOrderResponseEntity = customerService.SubmitCustomerOrder(customerId, orderRequest);

        if (customerOrderResponseEntity.getStatusCode().is2xxSuccessful() && customerOrderResponseEntity.getBody() != null) {
            SubmitCustomerOrderResponse submitCustomerOrderResponse = customerOrderResponseEntity.getBody();
            order.setOrderId(submitCustomerOrderResponse.getOrderId());
            order.setState(OrderState.Submitted);
        }
    }

}