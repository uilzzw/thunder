package com.thunder.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static Map<String,String> restMap(String path,String uri){
        Map<String ,String> map = new HashMap<>();
        Pattern pattern2 = Pattern.compile("/:(.+?)/");
        if (path.endsWith("/")==false){
            path= path+"/";
        }
        Matcher matcher = pattern2.matcher(path);
        String[] path_s =(path.substring(1,path.length())).split("/");
        String[] uri_s = (uri.substring(1,uri.length())).split("/");
        while (matcher.find()){
            for (int i =0 ;i<path_s.length;i++){
                if (path_s[i].equals(path.substring(matcher.start()+1,matcher.end()-1))){
                    map.put(path.substring(matcher.start()+2,matcher.end()-1),uri_s[i]);
                }
            }
        }
        return map;
    }


}
