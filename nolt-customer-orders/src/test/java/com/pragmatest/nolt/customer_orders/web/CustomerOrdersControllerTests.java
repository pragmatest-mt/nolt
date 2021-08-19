package com.pragmatest.nolt.customer_orders.web;

import com.pragmatest.nolt.customer_orders.services.CustomerOrdersService;
import com.pragmatest.nolt.customer_orders.web.controllers.CustomerOrdersController;
import com.pragmatest.nolt.customer_orders.web.requests.OrderItem;
import com.pragmatest.nolt.customer_orders.web.requests.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.responses.SubmitOrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerOrdersControllerTests {

    @Autowired
    CustomerOrdersController customerOrdersController;

    @MockBean
    CustomerOrdersService customerOrdersServiceMock;

    @Test
    public void testSubmitOrderValidOrder() {
        // Arrange

        String customerId = UUID.randomUUID().toString();
        SubmitOrderRequest request = new SubmitOrderRequest(List.of(new OrderItem("burger", 1, "no lettuce")));

        String expectedOrderId = UUID.randomUUID().toString();
        when(customerOrdersServiceMock.submitOrder()).thenReturn(expectedOrderId);

        // Act

        SubmitOrderResponse actualResponse = customerOrdersController.submit(customerId, request);

        // Assert

        assertNotNull(actualResponse, "Response is null.");

        String id = actualResponse.getId();
        assertNotNull(id, "Id in response is null.");

        assertEquals(expectedOrderId, id);
    }

}
