package com.pragmatest.nolt.restaurants.service.models;

import java.util.List;

public class Order {

    String userId;
    String orderId;
    List<OrderItem> orderItems;

    public Order(String userId, String orderId, List<OrderItem> orderItems) {
        this.userId = userId;
        this.orderId = orderId;
        this.orderItems = orderItems;
    }

    public Order(String userId, List<OrderItem> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
