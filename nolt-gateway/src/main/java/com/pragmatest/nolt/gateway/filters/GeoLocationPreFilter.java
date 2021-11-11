package com.pragmatest.nolt.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.pragmatest.nolt.gateway.web.IpClient;
import com.pragmatest.nolt.gateway.web.IpStackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class GeoLocationPreFilter extends ZuulFilter {

    @Autowired
    IpClient ipClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String ipAddress = request.getHeader("X-Forwarded-For");

        IpStackResponse ipStackResponse = ipClient.getGeoLocationData(ipAddress);

        if (ipAddress == null || ipAddress.isEmpty()) {
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("Access denied");
            ctx.setSendZuulResponse(false);
        } else if (!Objects.equals(ipStackResponse.getContinentName(), "Europe")) {
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("We are not in your country yet.");
            ctx.setSendZuulResponse(false);

            System.out.println("User is not in Europe but in " + ipStackResponse.getContinentName());
        } else {
            ctx.getZuulRequestHeaders().put("X-Customer-Country", ipStackResponse.getCountryName());
            System.out.println("User is in " + ipStackResponse.getCountryName());
        }

        return null;
    }
}


