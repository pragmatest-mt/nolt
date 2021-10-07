package com.pragmatest.nolt.customer_orders.web;

import com.pragmatest.nolt.customer_orders.services.CustomerOrdersService;
import com.pragmatest.nolt.customer_orders.services.models.OrderSubmission;
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

import static com.pragmatest.nolt.customer_orders.helpers.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        when(customerOrdersServiceMock.submitOrder(any(OrderSubmission.class))).thenReturn(expectedOrderId);

        // Act

        SubmitOrderResponse actualResponse = customerOrdersController.submit(customerId, request);

        // Assert

        assertNotNull(actualResponse, "Response is null.");

        String id = actualResponse.getOrderId();
        assertNotNull(id, "Id in response is null.");

        assertEquals(expectedOrderId, id);

        verify(customerOrdersServiceMock, times(1)).submitOrder(any(OrderSubmission.class));
    }

    @Test
    public void testGetOrderValidId() {
        // Arrange

        String customerId = UUID.randomUUID().toString();
        String orderId = UUID.randomUUID().toString();

        // TODO - mock the customerOrdersServiceMock's getOrder method. When given the orderId, this method should return an order

        // Act

        // TODO - call the get() method inside the controller and save the response in a variable

        // Assert

        // TODO - verify that the customerOrdersServiceMock's getOrder method was called
        // TODO - assert the response matches has the same values as that given by the method mocked in the service
    }

}
