package com.stackroute.APIGateway.filters;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * This filter is Called after the routing process is completed
 */
@Slf4j
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "Post Route Filter";
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
        log.info("at Post Filter");
        return null;
    }

}