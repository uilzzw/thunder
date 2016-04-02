package com.thunder.route;

import com.thunder.wrapper.Request;
import com.thunder.wrapper.Response;

/**
 * Created by icepoint1999 on 3/17/16.
 */
public interface RouteHandler {

    public void handle(Request request ,Response response);
}
