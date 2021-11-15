package com.pragmatest.nolt.customer_orders.data.entities;

import com.pragmatest.nolt.customer_orders.enums.OrderState;

import javax.persistence.*;
import java.util.List;

@Entity
public class CustomerOrderEntity {

    @ElementCollection
    @CollectionTable(name="ORDER_ITEMS", joinColumns=@JoinColumn(name="ORDER_ID"))
    private List<OrderItem> orderItems;

    private String customerId;

    @Id
    private String orderId;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public CustomerOrderEntity(List<OrderItem> orderItems, String customerId, String orderId) {
        this.orderItems = orderItems;
        this.customerId = customerId;
        this.orderId = orderId;
    }

    public CustomerOrderEntity() {
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
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

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
