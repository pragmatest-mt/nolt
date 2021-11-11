package com.pragmatest.nolt.specifications.common.services.customers;

import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderRequest;
import com.pragmatest.nolt.specifications.common.models.customers.CustomerSubmitOrderResponse;
import com.pragmatest.nolt.specifications.common.models.customers.GetCustomerOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class CustomersOrderServiceImpl implements CustomersOrderService {

    @Value(value = "${customers-service.submit-customer-order.url-template}")
    String submitCustomerOrderUrl;

    @Value(value = "${customers-service.get-customer-order.url-template}")
    String getCustomerOrderUrl;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<CustomerSubmitOrderResponse> SubmitCustomerOrder(String customerId, CustomerSubmitOrderRequest request) {
        HttpEntity<CustomerSubmitOrderRequest> requestEntity = new HttpEntity<>(request, getDefaultHeaders(customerId));

        ResponseEntity<CustomerSubmitOrderResponse> customerSubmitOrderResponseResponseEntity = restTemplate.exchange(
                submitCustomerOrderUrl, HttpMethod.POST,
                requestEntity, CustomerSubmitOrderResponse.class);

        return customerSubmitOrderResponseResponseEntity;
    }

    @Override
    public ResponseEntity<GetCustomerOrderResponse> GetCustomerOrder(String customerId, String orderId) {
        String url = getCustomerOrderUrl;

        HttpEntity request = new HttpEntity(getDefaultHeaders(customerId));

        ResponseEntity<GetCustomerOrderResponse> response = restTemplate.exchange(
                url, HttpMethod.GET,
                request, GetCustomerOrderResponse.class, orderId);

        return response;
    }

    private HttpHeaders getDefaultHeaders(String customerId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-Customer-Id", customerId);
        httpHeaders.set("X-Forwarded-For", "88.203.10.242");
        return httpHeaders;
    }

}
