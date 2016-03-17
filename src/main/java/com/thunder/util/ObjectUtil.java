package com.thunder.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by icepoint1999 on 3/17/16.
 */
public class ObjectUtil {


    public static Field[] getFileds(Object o){

        Field[] fields = o.getClass().getDeclaredFields();
        return fields;
    }

    public static Map<String,Object> getFiledValue(Object o){
        Map map = new HashMap();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            try {
                String value = null==field.get(o) ? "" : field.get(o).toString();
                map.put(field.getName(),field.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }



}
