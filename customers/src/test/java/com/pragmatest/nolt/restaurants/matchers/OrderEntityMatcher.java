package com.pragmatest.nolt.restaurants.matchers;

import com.pragmatest.nolt.restaurants.data.entities.OrderEntity;
import org.mockito.ArgumentMatcher;
import com.cedarsoftware.util.DeepEquals;


public class OrderEntityMatcher implements ArgumentMatcher<OrderEntity> {
    private OrderEntity left;

    public OrderEntityMatcher(OrderEntity left) {
        this.left = left;
    }

    @Override
    public boolean matches(OrderEntity right) {
        if (left == right) return true;
        if (right == null) return false;

        return  // TODO check øn this
                // left.getOrderId().equals(right.getOrderId()) &&
                left.getState().equals(right.getState()) &&
                left.getUserId().equals(right.getUserId()) &&
                DeepEquals.deepEquals(left.getOrderItems(), right.getOrderItems());
    }
}
