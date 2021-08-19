package com.pragmatest.nolt.specifications.common.models.restaurants;

import org.joda.time.DateTime;

public class RestaurantAcceptOrderRequest {

    private DateTime estimatedDeliveryTime;

    public RestaurantAcceptOrderRequest(DateTime estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public RestaurantAcceptOrderRequest() {
    }

    public DateTime getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(DateTime estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
}
