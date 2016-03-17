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
    private Method action;


    //路由方式

    private String method;

    //控制target

    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

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

    public Method getAction() {
        return action;
    }

    public void setAction(Method action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Route(String path, Object controller, Method action, String method) {
        this.path = path;
        this.controller = controller;
        this.action = action;
        this.method = method;
    }
}
