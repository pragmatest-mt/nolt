package com.pragmatest.nolt.specifications.stepdefinitions;

import com.pragmatest.nolt.specifications.common.services.restaurants.ResturantsOrderService;
import com.pragmatest.nolt.specifications.common.state.Order;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class ResturantOrderAcceptanceSteps {

    @Autowired
    ResturantsOrderService resturantOrderService;

    @Autowired
    Order order;

    @When("the restaurant accepts the order")
    public void theRestaurantAcceptsTheOrder() {
        throw new PendingException();
    }
}
