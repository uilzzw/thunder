package com.thunder.util;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public class Response {

    private  HttpServletResponse httpServletResponse;

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public Response(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
}
