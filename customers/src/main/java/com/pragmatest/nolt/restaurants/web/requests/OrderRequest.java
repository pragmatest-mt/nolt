package com.pragmatest.nolt.restaurants.web.requests;

import java.util.List;

public class OrderRequest {

    String userId;
    List<OrderItem> orderItems;

    public OrderRequest(String userId, List<OrderItem> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public OrderRequest() {
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
