package com.pragmatest.nolt.restaurant_orders.data.entities;

import com.pragmatest.nolt.restaurant_orders.common.enums.OrderState;

import javax.persistence.*;
import java.util.List;

@Entity
public class RestaurantOrderEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="ORDER_ITEMS", joinColumns=@JoinColumn(name="ORDER_ID"))
    private List<OrderItem> orderItems;

    @Id
    private String orderId;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public RestaurantOrderEntity(List<OrderItem> orderItems, String orderId, OrderState orderState) {
        this.orderItems = orderItems;
        this.orderId = orderId;
        this.orderState = orderState;
    }

    public RestaurantOrderEntity() {
    }

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
}