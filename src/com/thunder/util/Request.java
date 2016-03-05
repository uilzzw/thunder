package com.thunder.util;

import javax.servlet.http.HttpServletRequest;

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

}
