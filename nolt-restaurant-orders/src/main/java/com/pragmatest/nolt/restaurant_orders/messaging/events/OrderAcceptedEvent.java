package com.pragmatest.nolt.restaurant_orders.messaging.events;

public class OrderAcceptedEvent {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
