package com.pragmatest.nolt.customer_orders.helpers;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class Assertions {

    public static void assertIsValidUuid(String id)
    {  try {
            UUID uuid = UUID.fromString(id);
            assertEquals(id, uuid.toString());
        } catch (IllegalArgumentException e) {
            fail(id + " is not a valid UUID.");
        }
    }
}
