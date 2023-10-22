package com.bastiaanjansen;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class VersionedRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private final ApiVersioningProperties properties;

    @Override
    protected RequestCondition<?> getCustomTypeCondition(@NonNull Class<?> handlerType) {
        return createRequestCondition(handlerType);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(@NonNull Method method) {
        return createRequestCondition(method);
    }

    private ApiVersioningRequestCondition createRequestCondition(AnnotatedElement element) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(element, ApiVersion.class);

        if (apiVersion == null) {
            return null;
        }

        return new ApiVersioningRequestCondition(apiVersion.value().trim(), properties);
    }
}
