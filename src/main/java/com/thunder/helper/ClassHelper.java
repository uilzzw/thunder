package com.thunder.helper;

import com.thunder.Annotation.Controller;
import com.thunder.Annotation.Service;
import com.thunder.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public final class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassList(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    //获取所有service类

    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    //获取所有controller类
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }
    //获取所有带注解
    public static Set<Class<?>> getAspectClassSetByAnnotation(Class<? extends Annotation> c) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(c)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    //获取所有的bean
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanclassSet = new HashSet<Class<?>>();
        beanclassSet.addAll(getServiceClassSet());
        beanclassSet.addAll(getControllerClassSet());
        return beanclassSet;
    }
    //获取所有的子类
    public static Set<Class<?>> getBeanClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> beanclassSet = new HashSet<Class<?>>();
        for (Class<?> c : CLASS_SET){
            if (superClass.isAssignableFrom(c)&&!superClass.equals(c)){
                beanclassSet.add(c);
            }
        }
        return beanclassSet;
    }



}
