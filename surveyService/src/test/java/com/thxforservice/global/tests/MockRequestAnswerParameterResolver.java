package com.thxforservice.global.tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

public class MockRequestAnswerParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(MockRequestAnswer.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Method testMethod = extensionContext.getRequiredTestMethod();
        MockRequestAnswer annotation = testMethod.getAnnotation(MockRequestAnswer.class);

        if (annotation != null) {
            Long srvyNo = annotation.srvyNo();
            // Create a mock object with the annotation's values
            return new MockRequestAnswerImpl(srvyNo);
        }
        throw new ParameterResolutionException("No MockRequestAnswer annotation found.");
    }
}