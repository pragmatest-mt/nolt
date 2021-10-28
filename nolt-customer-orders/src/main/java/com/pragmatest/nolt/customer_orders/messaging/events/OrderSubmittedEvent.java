package com.pragmatest.nolt.customer_orders.messaging.events;

import com.pragmatest.nolt.customer_orders.enums.OrderState;

import java.util.List;

public class OrderSubmittedEvent {

    private List<OrderItem> orderItems;
    private String orderId;
    private OrderState orderState;
    private String customerId;

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

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderSubmittedEvent{" +
                "orderItems=" + orderItems +
                ", orderId='" + orderId + '\'' +
                ", orderState=" + orderState +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
