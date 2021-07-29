package com.pragmatest.nolt.restaurants.messaging.events;

import java.util.List;

public class OrderSubmittedEvent {

    private String orderId;
    private String userId;
    private List<OrderItem> orderItems;

    public OrderSubmittedEvent(String orderId, String userId, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public OrderSubmittedEvent() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
