package com.pragmatest.nolt.customer_orders.web;

import com.pragmatest.nolt.customer_orders.web.controllers.CustomerOrdersController;
import com.pragmatest.nolt.customer_orders.web.requests.OrderItem;
import com.pragmatest.nolt.customer_orders.web.requests.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.responses.SubmitOrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerOrdersControllerTests {

    @Autowired
    CustomerOrdersController customerOrdersController;

    @Test
    public void testSubmitOrderValidOrder() {
        // Arrange

        String customerId = UUID.randomUUID().toString();
        SubmitOrderRequest request = new SubmitOrderRequest(List.of(new OrderItem("burger", 1, "no lettuce")));

        // Act

        SubmitOrderResponse actualResponse = customerOrdersController.submit(customerId, request);

        // Assert

        assertNotNull(actualResponse, "Response is null.");

        String id = actualResponse.getId();
        assertNotNull(id, "Id in response is null.");

        try {
            UUID uuid = UUID.fromString(id);
            assertEquals(id, uuid.toString());
        } catch(IllegalArgumentException e) {
            fail(id + " is not a valid UUID.");
        }
    }

}
