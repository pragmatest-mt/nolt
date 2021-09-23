package com.pragmatest.nolt.specifications.stepdefinitions;

import com.pragmatest.nolt.specifications.common.services.customers.CustomersOrderService;
import com.pragmatest.nolt.specifications.common.state.Order;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerOrderSubmissionSteps {

    @Autowired
    CustomersOrderService customerService;

    @Autowired
    Order order;

    @Given("a customer submits an order to a restaurant")
    public void aCustomerSubmitsAnOrderToARestaurant() {
        throw new PendingException();
    }

}
