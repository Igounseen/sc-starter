package com.swx.sc.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author shiwenxiang
 * @date 2020/4/1
 */
@Configuration
public class BeanConfig {

    /**
     * 路由断言
     *
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> hello() {
        return RouterFunctions.route(RequestPredicates.path("/hello"), req -> ServerResponse.ok().body(BodyInserters.fromObject("hello")));
    }

    /**
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator locator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/baidu").uri("https://www.baidu.com/"))
                .route(r -> r.path("/sc-user/**").uri("lb://sc-user"))
                .route(r -> r.path("/sc-order/**").uri("lb://sc-order"))
                .build();
    }

}
