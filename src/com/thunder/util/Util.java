package com.thunder.util;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public class Util {

    //获取resouce的class object

    public static Object matchController(String name,String path){

        Object object = null ;

        try {
            object = Class.forName(path+"."+name);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }

}
