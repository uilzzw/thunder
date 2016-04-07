package com.thunder.helper;

import com.thunder.Annotation.Controller;
import com.thunder.Constant;
import com.thunder.util.ClassUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by icepoint1999 on 3/24/16.
 */
public class HelpLoader {

    public static void init(){
        Logger logger = LogManager.getLogger(HelpLoader.class);
        Class<?>[] classs={
                ClassHelper.class,
                BeanHelper.class,
                Aophelper.class,
                ControllerHelper.class,
                IocHelper.class};
        for (Class<?> cls:classs){
           logger.info(Constant.LOG_Aop_NAME+"Load Class: " + cls.getName() + " success!");
            ClassUtil.loadClass(cls.getName(),true);
        }

    }
}
