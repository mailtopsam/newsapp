package com.stackroute.APIGateway.filters;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * This filter is used to route the request
 */
@Slf4j
public class RouteFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "Route Filter";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("Inside RouteFilter");

        return null;
    }

}