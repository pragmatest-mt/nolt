package com.pragmatest.nolt.restaurants.web.responses;

import com.pragmatest.nolt.restaurants.web.requests.OrderItem;

import java.util.List;

public class OrderResponse {

    String orderId;
    String userId;
    List<OrderItem> orderItems;

    public OrderResponse(String orderId, String userId, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public OrderResponse() {
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
