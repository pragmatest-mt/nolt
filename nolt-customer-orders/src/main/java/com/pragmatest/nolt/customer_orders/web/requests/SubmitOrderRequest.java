package com.pragmatest.nolt.customer_orders.web.requests;

import java.util.List;

public class SubmitOrderRequest {

    private List<OrderItem> orderItems;

    public SubmitOrderRequest(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public SubmitOrderRequest() {
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}