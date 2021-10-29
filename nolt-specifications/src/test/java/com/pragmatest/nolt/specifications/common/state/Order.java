package com.pragmatest.nolt.specifications.common.state;

import io.cucumber.spring.ScenarioScope;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class Order {

    private String orderId;
    private String customerId;
    private DateTime expectedDeliveryDate;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public DateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(DateTime expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
