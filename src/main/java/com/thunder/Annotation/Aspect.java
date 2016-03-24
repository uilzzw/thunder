package com.thunder.Annotation;

import java.lang.annotation.*;

/**
 * Created by icepoint1999 on 3/24/16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value();
}
