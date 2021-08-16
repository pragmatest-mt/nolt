package com.pragmatest.nolt.customer_orders.web.responses;

public class SubmitOrderResponse {

    private String id;

    public SubmitOrderResponse(String id) {
        this.id = id;
    }

    public SubmitOrderResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
