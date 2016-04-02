package com.thunder.wrapper;

import com.thunder.core.Thunder;
import com.thunder.render.JspRender;
import com.thunder.render.Render;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    public void redirect_to(String path){
        try {
            httpServletResponse.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderJSON(Object o){

        try {
            render.render(o,httpServletResponse.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderJSON(Map<String ,Object> map){

        try {
            render.render(map,httpServletResponse.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
