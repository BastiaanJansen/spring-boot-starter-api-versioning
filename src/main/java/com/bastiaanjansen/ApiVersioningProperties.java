package com.bastiaanjansen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("api-versioning")
public class ApiVersioningProperties {

    private final ApiVersioningType type;

    private final String header;

    private final String param;

}
