package com.thunder.util;

import com.thunder.core.Thunder;
import com.thunder.render.JspRender;
import com.thunder.render.Render;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public class Response {


    private  HttpServletResponse httpServletResponse;

    private Render render =null;

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public Response(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        this.render = Thunder.zeus().getRender();
    }

    public  void render(String name){

       render.render(name,null);

    }

}
