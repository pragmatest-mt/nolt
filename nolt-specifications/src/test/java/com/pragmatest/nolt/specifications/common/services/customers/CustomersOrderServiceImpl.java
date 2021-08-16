package com.pragmatest.nolt.specifications.common.services.customers;

import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderRequest;
import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class CustomersOrderServiceImpl implements CustomersOrderService {

    @Value(value = "${customers-service.submit-customer-order.url-template}")
    String urlTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<CustomerSubmitOrderResponse> SubmitCustomerOrder(String customerId, CustomerSubmitOrderRequest request) {
        HttpEntity<CustomerSubmitOrderRequest> requestEntity = new HttpEntity<>(request, getDefaultHeaders(customerId));
        
        restTemplate.exchange(urlTemplate, HttpMethod.POST, requestEntity, CustomerSubmitOrderResponse.class);

        return new ResponseEntity(new CustomerSubmitOrderResponse(), HttpStatus.OK);
    }

    private HttpHeaders getDefaultHeaders(String customerId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Customer-Id", customerId);
        return httpHeaders;
    }

}
