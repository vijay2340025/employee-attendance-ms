package com.domain.config;

import com.domain.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    AuthFilter authFilter() {
        return new AuthFilter();
    }
}
