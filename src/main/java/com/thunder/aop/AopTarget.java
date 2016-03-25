package com.thunder.aop;

import java.lang.annotation.Annotation;

/**
 * Created by icepoint1999 on 3/25/16.
 */
public class AopTarget {
    private Class<? extends  Annotation> annotation;
    private Class cls;

    public Class<? extends Annotation> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
