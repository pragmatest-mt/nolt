package com.pragmatest.nolt.customer_orders.services.models;

import com.pragmatest.nolt.customer_orders.enums.OrderState;

import java.util.List;

public class Order {
    String customerId;
    String orderId;
    List<OrderItem> orderItems;
    OrderState orderState;


    public Order(String customerId, String orderId, List<OrderItem> orderItems) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderItems = orderItems;
    }

    public Order(String customerId, List<OrderItem> orderItems) {
        this.customerId = customerId;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
