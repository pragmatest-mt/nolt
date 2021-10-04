package com.pragmatest.nolt.customer_orders.services;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.customer_orders.data.entities.CustomerOrderEntity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
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

class CustomerOrderEntityMatcher implements ArgumentMatcher<CustomerOrderEntity> {

    private CustomerOrderEntity left;
    private CustomerOrderEntity match;

    public CustomerOrderEntityMatcher(CustomerOrderEntity left) {
        this.left = left;
    }

    @Override
    public boolean matches(CustomerOrderEntity right) {
        boolean isMatch = left != null && right != null &&
                left.getCustomerId().equals(right.getCustomerId()) &&
                DeepEquals.deepEquals(left.getOrderItems(), right.getOrderItems()) &&
                isValidUUID(right.getOrderId());

        if (isMatch) {
            match = right;
        }

        return isMatch;
    }

    public CustomerOrderEntity getMatch() {
        return match;
    }

    private boolean isValidUUID(String id) {

        if (id == null) return false;

        try {
            UUID uuid = UUID.fromString(id);
            return id.equals(uuid.toString());
        } catch(IllegalArgumentException e) {
            return false;
        }
    }
}