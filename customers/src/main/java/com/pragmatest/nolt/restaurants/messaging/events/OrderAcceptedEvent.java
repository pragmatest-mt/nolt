package com.pragmatest.nolt.restaurants.messaging.events;

import java.util.Date;

public class OrderAcceptedEvent {

    String orderId;
    Date estimatedReadyTime;

    public OrderAcceptedEvent(String orderId, Date estimatedReadyTime) {
        this.orderId = orderId;
        this.estimatedReadyTime = estimatedReadyTime;
    }

    public OrderAcceptedEvent() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getEstimatedReadyTime() {
        return estimatedReadyTime;
    }

    public void setEstimatedReadyTime(Date estimatedReadyTime) {
        this.estimatedReadyTime = estimatedReadyTime;
    }
}
