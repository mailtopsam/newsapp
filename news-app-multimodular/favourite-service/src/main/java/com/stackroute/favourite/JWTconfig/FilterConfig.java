package com.stackroute.favourite.JWTconfig;



import com.stackroute.favourite.JWTfilter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Indicates this as a configuration class
@Configuration
public class FilterConfig {
       //Create a bean for FilterRegistrationBean
    @Bean
    public FilterRegistrationBean jwtFilter() {
        //  Register the JwtFilter
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());
        // add URL pattern for all requests so that any request for that URL pattern will be intercepted by the filter
        filter.addUrlPatterns("/api/v1/*");
        return filter;
    }
}

