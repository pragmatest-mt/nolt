package com.pragmatest.nolt.specifications.common.services.customers;

import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderRequest;
import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderResponse;
import org.springframework.http.ResponseEntity;

public interface CustomersOrderService {

    ResponseEntity<CustomerSubmitOrderResponse> SubmitCustomerOrder(String customerId, CustomerSubmitOrderRequest request);


}
