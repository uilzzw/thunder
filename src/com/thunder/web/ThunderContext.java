package com.thunder.web;

import com.thunder.core.Thunder;
import com.thunder.util.Request;
import com.thunder.util.Response;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public final class ThunderContext {


    private static final ThreadLocal<ThunderContext> CONTEXT = new ThreadLocal<ThunderContext>();

    private ServletContext servletContext;

    private Request request;

    private Response response;

    private ThunderContext(){


    }

    public static void initContext(ServletContext servletContext , Request request ,Response response ){

                ThunderContext thunderContext =new  ThunderContext();

                thunderContext.request = request ;

                thunderContext.response = response;

                thunderContext.servletContext = servletContext;

                CONTEXT.set(thunderContext);

    }

    public static ThunderContext me(){

        return CONTEXT.get();

    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
