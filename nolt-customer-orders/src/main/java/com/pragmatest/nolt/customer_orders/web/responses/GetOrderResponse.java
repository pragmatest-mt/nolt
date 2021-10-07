package com.pragmatest.nolt.customer_orders.web.responses;

import com.pragmatest.nolt.customer_orders.web.requests.OrderItem;

import java.util.List;

public class GetOrderResponse {

    String customerId;
    String orderId;
    List<OrderItem> orderItems;

    public GetOrderResponse(String customerId, String orderId, List<OrderItem> orderItems) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderItems = orderItems;
    }

    public GetOrderResponse(String customerId, List<OrderItem> orderItems) {
        this.customerId = customerId;
        this.orderItems = orderItems;
    }

    public GetOrderResponse() {
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

}
