package com.pragmatest.nolt.customer_orders.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static com.pragmatest.nolt.customer_orders.helpers.Assertions.assertIsValidUuid;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CustomerOrdersServiceTests {

    @Autowired
    CustomerOrdersService customerOrdersService;

    @Test
    public void testSubmitOrder() {
        // Arrange

        // Act
        String id = customerOrdersService.submitOrder();

        // Assert
        assertNotNull(id, "Id in response is null.");
        assertIsValidUuid(id);
    }
}