package com.pragmatest.nolt.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomerAuthPreFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        return request.getServletPath().contains("customer");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String customerId = request.getHeader("X-Customer-Id");

        if (customerId == null || customerId.isEmpty()) {
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("access denied");
            ctx.setSendZuulResponse(false);
        }

        return null;
    }


}
