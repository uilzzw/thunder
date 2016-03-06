package com.thunder.render;

import com.thunder.core.Thunder;
import com.thunder.util.PathUtil;
import com.thunder.web.ThunderContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by icepoint1999 on 3/1/16.
 */
public class JspRender  implements  Render{

    public  void render(String view, Writer writer) {

    String viewPath = PathUtil.getViewPath(view);

        System.out.print(viewPath);

        HttpServletRequest httpServletRequest = ThunderContext.me().getRequest().getServletRequest();

        HttpServletResponse httpServletResponse = ThunderContext.me().getResponse().getHttpServletResponse();

        try {
            httpServletRequest.getRequestDispatcher(viewPath).forward(httpServletRequest,httpServletResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
