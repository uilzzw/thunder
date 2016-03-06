package com.thunder.util;

import com.thunder.User;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public  class Request {

        private HttpServletRequest servletRequest;

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public Request(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String params(String name){

       return  servletRequest.getParameter(name);

    }

    public int paramsAsInt(String name){

         return Integer.parseInt(servletRequest.getParameter(name));

    }

    public int resourceId(){

        return Integer.parseInt(servletRequest.getRequestURI().split("/")[2]);

    }

    public  Object getModel(Class c){

        Map<String,String[]>  map =  servletRequest.getParameterMap();
        Field filed =null;
        Object object = null;
        try {
            object = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for(String key:map.keySet()){

            String clazzName = key.split("[.]")[0];

            String  variable = key.split("[.]")[1];

            if (clazzName.equals(Util.getclassName(c))){
                try {
                    filed = c.getDeclaredField(variable);

                    filed.setAccessible(true);

                    filed.set(object,map.get(key)[0]);
                } catch (NoSuchFieldException e) {
                   continue;
                } catch (IllegalAccessException e) {
                    continue;
                }

            }

        }


        return object;

    }

    public static void main(String args[]){

        try {
            System.out.print(User.class.getDeclaredField("pass"));
            Object object = User.class.newInstance();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

}
