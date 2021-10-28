package com.pragmatest.nolt.specifications.common.models.customers;

import java.util.List;

public class CustomerSubmitOrderRequest {

    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
