package com.thxforservice.global.tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.Annotation;

public class MockRequestAnswerResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        // 매개변수가 MockRequestAnswer 타입인지 확인
        return parameterContext.getParameter().getType() == MockRequestAnswer.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        // MockRequestAnswer 객체를 생성하여 반환
        return new MockRequestAnswer() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return MockRequestAnswer.class;
            }

            @Override
            public long srvyNo() {
                return 1L; // 기본값 설정 (필요에 따라 변경 가능)
            }
        };
    }
}
