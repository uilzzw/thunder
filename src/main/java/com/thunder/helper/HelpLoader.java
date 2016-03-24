package com.thunder.helper;

import com.thunder.Annotation.Controller;
import com.thunder.util.ClassUtil;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public class HelpLoader {

    public static void init(){
        Class<?>[] classs={
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                Aophelper.class,
                ControllerHelper.class};
        for (Class<?> cls:classs){
            ClassUtil.loadClass(cls.getName(),true);
        }

    }
}
