package com.stackroute.APIGateway.filters;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;


 //This filter is called when there is an error during Gateway call
@Slf4j
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
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
        log.error("Inside Error Filter");
        return null;
    }

}