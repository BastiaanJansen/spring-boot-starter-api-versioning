package com.bastiaanjansen;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

@RequiredArgsConstructor
public class ApiVersioningRequestCondition implements RequestCondition<ApiVersioningRequestCondition> {

    private final String apiVersion;
    private final ApiVersioningProperties properties;

    @Override
    @NonNull
    public ApiVersioningRequestCondition combine(ApiVersioningRequestCondition other) {
        return new ApiVersioningRequestCondition(other.apiVersion, other.properties);
    }

    @Override
    public ApiVersioningRequestCondition getMatchingCondition(HttpServletRequest request) {
        ApiVersioningType type = properties.getType();

        boolean hasCorrectVersion = switch (type) {
            case HEADER -> handleHeader(request);
            case PARAM -> handleUri(request);
            default -> false;
        };

        return hasCorrectVersion ? this : null;
    }

    @Override
    public int compareTo(ApiVersioningRequestCondition other, @NonNull HttpServletRequest request) {
        return apiVersion.compareTo(other.apiVersion);
    }

    private boolean handleHeader(HttpServletRequest request) {
        String version = request.getHeader(properties.getHeader());

        return apiVersion.equalsIgnoreCase(version);
    }

    private boolean handleUri(HttpServletRequest request) {
        String version = request.getParameter(properties.getParam());

        return apiVersion.equalsIgnoreCase(version);
    }
}
