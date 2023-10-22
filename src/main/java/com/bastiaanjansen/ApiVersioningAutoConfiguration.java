package com.bastiaanjansen;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApiVersioningProperties.class)
public class ApiVersioningAutoConfiguration {

    @Bean
    public ApiVersioningMvcRegistrations apiVersioningMvcRegistrations(ApiVersioningProperties properties) {
        return new ApiVersioningMvcRegistrations(properties);
    }
}
