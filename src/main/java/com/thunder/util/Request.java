package com.thunder.util;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public  class Request {

        private HttpServletRequest servletRequest;


    public  HttpServletRequest getServletRequest() {
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

    public  Object getModel(Class<?> c){

        @SuppressWarnings("unchecked")
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
            String  variable = key;
            try {
                    filed = c.getDeclaredField(variable);
                    filed.setAccessible(true);
                    Type type = filed.getType();
                    filed.set(object,map.get(key)[0]);
                } catch (NoSuchFieldException e) {
                   continue;
                } catch (IllegalAccessException e) {
                    continue;
                }


        }


        return object;

    }

    public void sendParams(String name ,Object o){
        servletRequest.setAttribute(name,o);
    }


    public Object getSession(String name){

        return servletRequest.getSession().getAttribute(name);

    }
    public void setSession(String name,Object o){

      servletRequest.getSession().setAttribute(name,o);

    }

    public String getUri(){

        return servletRequest.getRequestURI();

    }



}
