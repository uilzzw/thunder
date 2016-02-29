package com.thunder.route;

import java.lang.reflect.Method;

/**
 * Created by icepoint1999 on 2/29/16.
 */
public class Route {

//        路由
    private String path;

    //所在的控制器
    private Object controller;

//    所执行的action
    private Method method;

    public Route() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}