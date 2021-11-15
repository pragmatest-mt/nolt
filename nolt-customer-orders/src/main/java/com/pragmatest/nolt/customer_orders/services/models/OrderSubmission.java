package com.pragmatest.nolt.customer_orders.services.models;

import java.util.List;

public class OrderSubmission {
    private List<OrderItem> orderItems;
    private String customerId;

    public OrderSubmission(String customerId, List<OrderItem> orderItems, String orderId) {
        this.customerId = customerId;
        this.orderItems = orderItems;
    }

    public OrderSubmission() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
