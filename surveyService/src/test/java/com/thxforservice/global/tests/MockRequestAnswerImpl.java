package com.thxforservice.global.tests;

import java.lang.annotation.Annotation;

public class MockRequestAnswerImpl implements MockRequestAnswer{
    private final long srvyNo;

    public MockRequestAnswerImpl(long srvyNo) {
        this.srvyNo = srvyNo;
    }

    @Override
    public long srvyNo() {
        return srvyNo;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return MockRequestAnswer.class;
    }
}
