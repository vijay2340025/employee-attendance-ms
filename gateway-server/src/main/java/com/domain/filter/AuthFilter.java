package com.domain.filter;

import com.domain.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouteValidator validator;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            validateUserToken(exchange);

            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            log.info("Reached GatewayFilter with" + exchange.getRequest().getPath().value());
            return chain.filter(exchange.mutate().request(builder.build()).build());
        };
    }

    private void validateUserToken(ServerWebExchange exchange) {
        if (validator.isSecured.test(exchange.getRequest())) {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                log.info("missing authorization header");
                throw new RuntimeException("missing authorization header");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            }
            try {
                jwtUtil.validateToken(authHeader);
                log.info("JWT token validated");
            } catch (Exception e) {
                log.info("invalid access...!");
                throw new RuntimeException("un authorized access to application");
            }
        }
    }

    public static class Config {
    }
}
