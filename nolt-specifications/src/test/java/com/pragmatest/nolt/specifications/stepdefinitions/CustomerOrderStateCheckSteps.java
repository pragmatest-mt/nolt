package com.pragmatest.nolt.specifications.stepdefinitions;

import com.pragmatest.nolt.specifications.common.services.customers.CustomersOrderService;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerOrderStateCheckSteps {

    @Autowired
    CustomersOrderService customersOrderService;

    @Then("^the customer's order is \'(.+)\'$")
    public void theCustomerSOrderIsAccepted(String expectedState) {

    }

}
