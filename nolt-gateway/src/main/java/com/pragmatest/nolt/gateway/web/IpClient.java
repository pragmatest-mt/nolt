package com.pragmatest.nolt.gateway.web;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IpClient {

    @Autowired
    RestTemplate restTemplate;

    public IpStackResponse getGeoLocationData(String ipAddress) {
       IpStackResponse response =  restTemplate
                .getForObject("http://api.ipstack.com/" + ipAddress + "?access_key=7ef7486e854da36c9fdc726e895acca9",
                        IpStackResponse.class );
        return response;
    }
}


