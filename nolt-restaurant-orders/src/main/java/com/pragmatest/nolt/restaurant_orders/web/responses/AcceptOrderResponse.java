package com.pragmatest.nolt.restaurant_orders.web.responses;

import com.pragmatest.nolt.restaurant_orders.common.enums.OrderState;

import java.util.List;

public class AcceptOrderResponse {
    String orderId;
    List<OrderItem> orderItems;
    private OrderState orderState;

    public AcceptOrderResponse(String orderId, List<OrderItem> orderItems, OrderState orderState) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.orderState = orderState;
    }

    public AcceptOrderResponse() {
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

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
