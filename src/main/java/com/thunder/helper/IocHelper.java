package com.thunder.helper;

import com.thunder.Annotation.Inject;
import com.thunder.util.MethodUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public final class IocHelper {

    static{
        Map<Class<?> ,Object> beanMap = BeanHelper.getBeanMap();
        if (!beanMap.isEmpty()){
            for (Map.Entry<Class<?>,Object> beanEntry : beanMap.entrySet()){
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取bean类定义的所有成员变量
                Field[] beanFields  =  beanClass.getDeclaredFields();
                if (beanFields.length!=0){
                    //遍历bean
                    for (Field field : beanFields){
                        if (field.isAnnotationPresent(Inject.class)){
                            field.setAccessible(true);
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            System.out.println(beanFieldInstance);
                            if (null != beanFieldInstance){
                                MethodUtil.setField(beanInstance,field,beanFieldInstance);
                                try {
                                    System.out.print(field.get(beanInstance));
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
