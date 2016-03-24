package com.thunder.helper;

import com.thunder.Annotation.Action;
import com.thunder.core.Thunder;
import com.thunder.util.ObjectUtil;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public class ControllerHelper {

    static{
        Thunder thunder = Thunder.zeus();
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (!controllerClassSet.isEmpty()){
            for (Class<?> controllerClass : controllerClassSet){
                //获取controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (methods.length!=0){
                    for (Method method : methods){
                        if (method.isAnnotationPresent(Action.class)){
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            String uMethod = action.method();
                           thunder.addRoute(mapping,uMethod,method.getName(), BeanHelper.getBean(controllerClass));

                        }

                    }
                }
            }
        }

    }
}
