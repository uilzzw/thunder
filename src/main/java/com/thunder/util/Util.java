package com.thunder.util;

import java.lang.reflect.Field;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public class Util {


    public static String getclassName(Class  T){

         String path = T.getName();

        String[] var = path.split("[.]");

        return var[var.length-1];
    }

    public static void setVar(Class T){

        try {
            Object object = T.newInstance();
            Field field = T.getDeclaredField("name");
            field.setAccessible(true);
            field.set(object,"ss");
            System.out.println(field.get(object));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }



    }

}
