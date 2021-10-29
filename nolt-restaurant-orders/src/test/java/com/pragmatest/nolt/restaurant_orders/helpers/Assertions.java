package com.pragmatest.nolt.restaurant_orders.helpers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
