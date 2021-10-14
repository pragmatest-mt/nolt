package com.pragmatest.nolt.specifications.common.services.customers;

import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderRequest;
import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderResponse;
import com.pragmatest.nolt.specifications.common.models.customers.GetCustomerOrderResponse;
import org.springframework.http.ResponseEntity;

public interface CustomersOrderService {

    ResponseEntity<CustomerSubmitOrderResponse> SubmitCustomerOrder(String customerId, CustomerSubmitOrderRequest request);


    ResponseEntity<GetCustomerOrderResponse> GetCustomerOrder(String customerId, String orderId);
}
