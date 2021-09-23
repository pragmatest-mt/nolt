package com.pragmatest.nolt.specifications.stepdefinitions;

import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderRequest;
import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderResponse;
import com.pragmatest.nolt.specifications.common.services.customers.CustomersOrderService;
import com.pragmatest.nolt.specifications.common.state.Order;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class CustomerOrderSubmissionSteps {

    @Autowired
    CustomersOrderService customerService;

    @Autowired
    Order order;

    @Given("a customer submits an order to a restaurant")
    public void aCustomerSubmitsAnOrderToARestaurant() {
        String customerId = "018777ce-8730-46ae-800c-9a1e950e05c0";
        CustomerSubmitOrderRequest request = new CustomerSubmitOrderRequest();

        // Use the customer service to submit an order
        ResponseEntity<CustomerSubmitOrderResponse> response = customerService.SubmitCustomerOrder(customerId, request);
        CustomerSubmitOrderResponse responseBody = response.getBody();

        order.setOrderId(responseBody.getOrderId());
    }

}
