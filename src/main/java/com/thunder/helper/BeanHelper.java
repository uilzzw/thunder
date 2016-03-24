package com.thunder.helper;

import com.thunder.util.ObjectUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public final class BeanHelper {
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();

    static{
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> bean : beanClassSet){
            Object object = ObjectUtil.newInstance(bean);
            BEAN_MAP.put(bean,object);

        }
    }

    public  static  Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取bean实体
     */
    public static <T> T getBean(Class<T> tClass){
        if (!BEAN_MAP.containsKey(tClass)){
            throw  new RuntimeException("can not get bean by class" + tClass);
        }

        return (T) BEAN_MAP.get(tClass);
    }
}
