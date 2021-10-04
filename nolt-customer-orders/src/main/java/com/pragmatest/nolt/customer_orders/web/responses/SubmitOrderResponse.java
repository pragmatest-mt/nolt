package com.pragmatest.nolt.customer_orders.web.responses;

public class SubmitOrderResponse {

    private String orderId;

    public SubmitOrderResponse(String orderId) {
        this.orderId = orderId;
    }

    public SubmitOrderResponse() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}