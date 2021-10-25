package com.pragmatest.nolt.restaurant_orders.messaging.events;

import com.pragmatest.nolt.restaurant_orders.common.enums.OrderState;

import java.util.List;

public class OrderSubmittedEvent {

    private List<OrderItem> orderItems;
    private String orderId;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderSubmittedEvent{" +
                "orderItems=" + orderItems +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
