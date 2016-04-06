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
                Aophelper.class,
                ControllerHelper.class,
                IocHelper.class};
        for (Class<?> cls:classs){
            System.out.println("Thunder:---------> load class " + cls + "success !");
            ClassUtil.loadClass(cls.getName(),true);
        }

    }
}
