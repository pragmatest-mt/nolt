package com.pragmatest.nolt.specifications.stepdefinitions;

import com.pragmatest.nolt.specifications.common.models.customers.GetCustomerOrderResponse;
import com.pragmatest.nolt.specifications.common.services.customers.CustomersOrderService;
import com.pragmatest.nolt.specifications.common.state.Order;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerOrderStateCheckSteps {

    @Autowired
    CustomersOrderService customersOrderService;

    @Autowired
    Order order;

    @Then("^the customer's order is \'(.+)\'$")
    public void theCustomerSOrderIsAccepted(String expectedState) {
        String customerId = order.getCustomerId();
        String orderId = order.getOrderId();

        ResponseEntity<GetCustomerOrderResponse> customerOrderResponseEntity = customersOrderService.GetCustomerOrder(customerId, orderId);

        assertEquals(HttpStatus.OK, customerOrderResponseEntity.getStatusCode());

        GetCustomerOrderResponse customerOrderResponse = customerOrderResponseEntity.getBody();

        assertNotNull(customerOrderResponse);
        assertEquals(expectedState, customerOrderResponse.getState());
    }

}
